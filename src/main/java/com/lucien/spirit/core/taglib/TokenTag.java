package com.lucien.spirit.core.taglib;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.UUID;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import com.lucien.spirit.core.constants.TokenConstants;

/**
 * @Filename : TokenTag.java
 * @Package : com.lucien.spirit.core.taglib
 * @Description : TODO
 * @author : lijunf
 * @CreateDate : 2016年2月14日
 */
public class TokenTag extends TagSupport {

    private static final long serialVersionUID = -5060882405545964960L;
    
    public String INPUT_TEXT = "<input type=\"hidden\" name=\"{0}\" value=\"{1}\">";

    public int doStartTag() throws JspException {
        String tokenValue = UUID.randomUUID().toString();
        
        pageContext.getSession().setAttribute(TokenConstants.DEFAULT_TOKEN_NAME, tokenValue);
        
        String text = MessageFormat.format(INPUT_TEXT, TokenConstants.DEFAULT_TOKEN_NAME, tokenValue);;

        JspWriter out = pageContext.getOut();
        try {
            out.print(text);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return SKIP_BODY;
    }
}
