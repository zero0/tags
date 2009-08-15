package org.zero0.tags.masterpage;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

public class MasterPageTag extends TagSupport {
	private static final long serialVersionUID = 3241864617982573832L;

	private String masterPage;
	@Override
	public int doStartTag() throws JspException {
		return EVAL_BODY_INCLUDE;
	}
	
	@Override
	public int doEndTag() throws JspException {
		try {
			pageContext.include( masterPage );
		} catch( Exception e ) {
		}
		return EVAL_PAGE;
	}

	public final void setMasterPage( String masterPage ) {
		this.masterPage = masterPage;
	}
}
