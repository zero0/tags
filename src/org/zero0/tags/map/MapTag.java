package org.zero0.tags.map;

import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

public class MapTag extends TagSupport implements MapConstants {
	private static final long serialVersionUID = 4501039097314209642L;

	private Map<String, String> map;
	
	public MapTag() {
	
	}
	
	@Override
	public int doStartTag() throws JspException {
		boolean skip = true;
		if( map != null ) {
			pageContext.setAttribute( CURRENT_MAP, map );
			skip = false;
		}
		return skip ? SKIP_BODY : EVAL_BODY_INCLUDE;
	}
	
	@Override
	public int doEndTag() throws JspException {
		pageContext.setAttribute( CURRENT_MAP, null );
		return EVAL_PAGE;
	}
	
	public final void setMap( Map<String, String> map ) {
		this.map = map;
	}
}
