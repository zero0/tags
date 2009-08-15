package org.zero0.tags.masterpage;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

public class ContentTag extends BodyTagSupport implements MasterPageConstants {
	private static final long serialVersionUID = 8716394424770379403L;
	
	private String name;
	@Override
	public int doStartTag() throws JspException {
		return EVAL_BODY_BUFFERED;
	}
	
	@Override
	public int doEndTag() throws JspException {
		String body = bodyContent.getString();
		pageContext.getRequest().setAttribute( MASTER_PAGE_CONTENT+name, body );
		return EVAL_PAGE;
	}

	public final void setName( String name ) {
		this.name = name;
	}
}
