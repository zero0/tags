package org.zero0.tags.map;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Pattern;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

@SuppressWarnings( "unchecked" )
public class EntryTag extends TagSupport implements MapConstants {
	private static final long serialVersionUID = -4206391944360168309L;

	private String select;
	private List<Map.Entry<String, String>> entries;
	private Map<String, String> map;
	private List<Map.Entry<String, String>> allEntries;
	private int entryIndex;

	public EntryTag() {
		select = null;
		entries = null;
	}

	@Override
	public int doStartTag() throws JspException {
		boolean skip = true;
		if( select != null ) {
			entries = new ArrayList<Map.Entry<String, String>>();
			map = (Map<String, String>)pageContext.findAttribute( CURRENT_MAP );
			allEntries = new ArrayList<Map.Entry<String, String>>( map.entrySet() );

			int index = select.indexOf( '*' );
			if( index < 0 ) {
				Map.Entry<String, String> e = findEntry( select );
				if( e != null ) {
					entries.add( e );
				}
			} else {
				if( select.equals( "*"  )) {
					entries.addAll( allEntries );
				} else {
					String selectWild = select.replace( "*", "(.+)" );
					List<Map.Entry<String, String>> e = findEntryWildcard( selectWild );
					if( !e.isEmpty() ) {
						entries.addAll( e );
					}
				}
			}
			skip = entries.isEmpty();
		}
		if( !skip ) {
			entryIndex = 0;
			pageContext.setAttribute( CURRENT_MAP_ENTRY, entries.get( entryIndex ) );
		}
		return skip ? SKIP_BODY : EVAL_BODY_INCLUDE;
	}

	@Override
	public int doAfterBody() throws JspException {
		boolean again = true;
		entryIndex++;
		
		if( entryIndex >= entries.size() ) {
			pageContext.setAttribute( CURRENT_MAP_ENTRY, null );
			again = false;
		} else {
			pageContext.setAttribute( CURRENT_MAP_ENTRY, entries.get( entryIndex ) );
		}
		return again ? EVAL_BODY_AGAIN : EVAL_PAGE;
	}

	@Override
	public int doEndTag() throws JspException {
		pageContext.setAttribute( CURRENT_MAP_ENTRY, null );
		return EVAL_PAGE;
	}

	public final void setSelect( String select ) {
		this.select = select;
	}

	private Map.Entry<String, String> findEntry( String key ) {
		for( Map.Entry<String, String> e : allEntries ) {
			if( e.getKey().equals( key ) ) {
				return e;
			}
		}
		return null;
	}
	
	private List<Map.Entry<String, String>> findEntryWildcard( String key ) {
		List<Map.Entry<String, String>> list = new ArrayList<Entry<String, String>>();
		Pattern p = Pattern.compile( key );
		
		for( Map.Entry<String, String> e : allEntries ) {
			if( p.matcher( e.getKey() ).matches() ) {
				list.add( e );
			}
		}
		return list;
	}
}
