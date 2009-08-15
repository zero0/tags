package org.zero0.tags.masterpage;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

public class PlaceholderTag extends BodyTagSupport implements MasterPageConstants {
	private static final long serialVersionUID = -4056219060636791534L;

	private String name;

	@Override
	public int doStartTag() throws JspException {
		return EVAL_BODY_BUFFERED;
	}

	@Override
	public int doAfterBody() throws JspException {
		
		return EVAL_PAGE;
	}
	
	@Override
	public int doEndTag() throws JspException {
		String body = (String)pageContext.getRequest().getAttribute( MASTER_PAGE_CONTENT + name );
		try {
			if( body == null ) {
				body = bodyContent.getString();
				if( body == null ) {
					return EVAL_PAGE;
				}
			}
			pageContext.getOut().append( body ).flush();	
		} catch( Exception e ) {
		}
		return EVAL_PAGE;
	}

	public final void setName( String name ) {
		this.name = name;
	}
}
