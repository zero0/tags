package org.zero0.tags.map;

import java.io.IOException;
import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

@SuppressWarnings( "unchecked" )
public class KeyTag extends TagSupport implements MapConstants {
	private static final long serialVersionUID = -1092968201702903537L;


	@Override
	public int doEndTag() throws JspException {
		JspWriter out = pageContext.getOut();
		try {
			String key = ((Map.Entry<String, String>)pageContext.findAttribute( CURRENT_MAP_ENTRY )).getKey();
			
			System.err.println("map key end tag: " + key );
			if( key != null ) out.write( key );
		} catch( IOException e ) {
		}
		return EVAL_PAGE;
	}
}
