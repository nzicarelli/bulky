/*
 * =============================================================================
 * 
 *   Copyright (c) 2011-2014, The THYMELEAF team (http://www.thymeleaf.org)
 * 
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 * 
 *       http://www.apache.org/licenses/LICENSE-2.0
 * 
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 * 
 * =============================================================================
 */
package com.bulky.support.email;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamSource;
import org.springframework.core.io.Resource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.bulky.account.AccountService;
import com.bulky.account.User;
import com.bulky.config.SpringMailConfig;
import com.bulky.customer.Customer;
import com.bulky.customer.CustomerRepository;
import com.bulky.support.AppUtil;
import com.bulky.support.Const;


@Service
public class EmailService {

    private static final String EMAIL_TEXT_TEMPLATE_NAME = "text/email-text";
    private static final String EMAIL_SIMPLE_TEMPLATE_NAME = "html/email-simple";
    private static final String EMAIL_WITHATTACHMENT_TEMPLATE_NAME = "html/email-withattachment";
    private static final String EMAIL_INLINEIMAGE_TEMPLATE_NAME = "html/email-inlineimage";
    private static final String EMAIL_EDITABLE_TEMPLATE_CLASSPATH_RES = "classpath:mail/editablehtml/email-editable.html";

    private static final String BACKGROUND_IMAGE = "mail/editablehtml/images/background.png";
    private static final String LOGO_BACKGROUND_IMAGE = "mail/editablehtml/images/logo-background.png";
    private static final String THYMELEAF_BANNER_IMAGE = "mail/editablehtml/images/thymeleaf-banner.png";
    private static final String THYMELEAF_LOGO_IMAGE = "mail/editablehtml/images/thymeleaf-logo.png";

    private static final String PNG_MIME = "image/png";

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    @Qualifier("emailTemplateEngine")
    private TemplateEngine htmlTemplateEngine;

    @Autowired
    @Qualifier("emailTemplateEngine")
    private TemplateEngine textTemplateEngine;

    @Autowired
    @Qualifier("emailTemplateEngine")
    private TemplateEngine stringTemplateEngine;
    
    
    private static Log log = LogFactory.getLog(EmailService.class);
	
	@Value("${account.server.name:http://localhost}")
    private String serverRoot;
	
	@Value("${account.email.from:no-reply@bulky.com}")
	private String fromEmail;
	
	@Value("${account.email.bcc:nzicarelli@kaala.it}")
	private String bcc;
		
	
	@Autowired
	private AccountService accountService;
	
	@Autowired
	private MessageSource messageSource;
	
	@Autowired
	private CustomerRepository custoRep;



    /* 
     * Send plain TEXT mail 
     */
    public void sendTextMail(
        final String recipientName, final String recipientEmail, final Locale locale)
        throws MessagingException {

        // Prepare the evaluation context
        final Context ctx = new Context(locale);
        ctx.setVariable("name", recipientName);
        ctx.setVariable("subscriptionDate", new Date());
        ctx.setVariable("hobbies", Arrays.asList("Cinema", "Sports", "Music"));

        // Prepare message using a Spring helper
        final MimeMessage mimeMessage = this.mailSender.createMimeMessage();
        final MimeMessageHelper message = new MimeMessageHelper(mimeMessage, "UTF-8");
        message.setSubject("Example plain TEXT email");
        message.setFrom("thymeleaf@example.com");
        message.setTo(recipientEmail);

        // Create the plain TEXT body using Thymeleaf
        final String textContent = this.textTemplateEngine.process(EMAIL_TEXT_TEMPLATE_NAME, ctx);
        message.setText(textContent);

        // Send email
        this.mailSender.send(mimeMessage);
    }


    /* 
     * Send HTML mail (simple) 
     */
    public void sendSimpleMail(
        final String recipientName, final String recipientEmail, final Locale locale)
        throws MessagingException {

        // Prepare the evaluation context
        final Context ctx = new Context(locale);
        ctx.setVariable("name", recipientName);
        ctx.setVariable("subscriptionDate", new Date());
        ctx.setVariable("hobbies", Arrays.asList("Cinema", "Sports", "Music"));

        // Prepare message using a Spring helper
        final MimeMessage mimeMessage = this.mailSender.createMimeMessage();
        final MimeMessageHelper message = new MimeMessageHelper(mimeMessage, "UTF-8");
        message.setSubject("Example HTML email (simple)");
        message.setFrom("thymeleaf@example.com");
        message.setTo(recipientEmail);

        // Create the HTML body using Thymeleaf
        final String htmlContent = this.htmlTemplateEngine.process(EMAIL_SIMPLE_TEMPLATE_NAME, ctx);
        message.setText(htmlContent, true /* isHtml */);

        // Send email
        this.mailSender.send(mimeMessage);
    }


    /* 
     * Send HTML mail with attachment. 
     */
    public void sendMailWithAttachment(
        final String recipientName, final String recipientEmail, final String attachmentFileName,
        final byte[] attachmentBytes, final String attachmentContentType, final Locale locale)
        throws MessagingException {

        // Prepare the evaluation context
        final Context ctx = new Context(locale);
        ctx.setVariable("name", recipientName);
        ctx.setVariable("subscriptionDate", new Date());
        ctx.setVariable("hobbies", Arrays.asList("Cinema", "Sports", "Music"));

        // Prepare message using a Spring helper
        final MimeMessage mimeMessage = this.mailSender.createMimeMessage();
        final MimeMessageHelper message
            = new MimeMessageHelper(mimeMessage, true /* multipart */, "UTF-8");
        message.setSubject("Example HTML email with attachment");
        message.setFrom("thymeleaf@example.com");
        message.setTo(recipientEmail);

        // Create the HTML body using Thymeleaf
        final String htmlContent = this.htmlTemplateEngine.process(EMAIL_WITHATTACHMENT_TEMPLATE_NAME, ctx);
        message.setText(htmlContent, true /* isHtml */);

        // Add the attachment
        final InputStreamSource attachmentSource = new ByteArrayResource(attachmentBytes);
        message.addAttachment(
            attachmentFileName, attachmentSource, attachmentContentType);

        // Send mail
        this.mailSender.send(mimeMessage);
    }


    /* 
     * Send HTML mail with inline image
     */
    public void sendMailWithInline(
        final String recipientName, final String recipientEmail, final String imageResourceName,
        final byte[] imageBytes, final String imageContentType, final Locale locale)
        throws MessagingException {

        // Prepare the evaluation context
        final Context ctx = new Context(locale);
        ctx.setVariable("name", recipientName);
        ctx.setVariable("subscriptionDate", new Date());
        ctx.setVariable("hobbies", Arrays.asList("Cinema", "Sports", "Music"));
        ctx.setVariable("imageResourceName", imageResourceName); // so that we can reference it from HTML

        // Prepare message using a Spring helper
        final MimeMessage mimeMessage = this.mailSender.createMimeMessage();
        final MimeMessageHelper message
            = new MimeMessageHelper(mimeMessage, true /* multipart */, "UTF-8");
        message.setSubject("Example HTML email with inline image");
        message.setFrom("thymeleaf@example.com");
        message.setTo(recipientEmail);

        // Create the HTML body using Thymeleaf
        final String htmlContent = this.htmlTemplateEngine.process(EMAIL_INLINEIMAGE_TEMPLATE_NAME, ctx);
        message.setText(htmlContent, true /* isHtml */);

        // Add the inline image, referenced from the HTML code as "cid:${imageResourceName}"
        final InputStreamSource imageSource = new ByteArrayResource(imageBytes);
        message.addInline(imageResourceName, imageSource, imageContentType);

        // Send mail
        this.mailSender.send(mimeMessage);
    }


    /* 
     * Send HTML mail with inline image
     */
    public String getEditableMailTemplate() throws IOException {
        final Resource templateResource = this.applicationContext.getResource(EMAIL_EDITABLE_TEMPLATE_CLASSPATH_RES);
        final InputStream inputStream = templateResource.getInputStream();
        return IOUtils.toString(inputStream, SpringMailConfig.EMAIL_TEMPLATE_ENCODING);
    }


    /*
     * Send HTML mail with inline image
     */
    public void sendEditableMail(
            final String recipientName, final String recipientEmail, final String htmlContent,
            final Locale locale)
            throws MessagingException {

        // Prepare message using a Spring helper
        final MimeMessage mimeMessage = this.mailSender.createMimeMessage();
        final MimeMessageHelper message
                = new MimeMessageHelper(mimeMessage, true /* multipart */, "UTF-8");
        message.setSubject("Example editable HTML email");
        message.setFrom("thymeleaf@example.com");
        message.setTo(recipientEmail);

        // Prepare the evaluation context
        final Context ctx = new Context(locale);
        ctx.setVariable("name", recipientName);
        ctx.setVariable("subscriptionDate", new Date());
        ctx.setVariable("hobbies", Arrays.asList("Cinema", "Sports", "Music"));

        // Create the HTML body using Thymeleaf
        final String output = stringTemplateEngine.process(htmlContent, ctx);
        message.setText(output, true /* isHtml */);

        // Add the inline images, referenced from the HTML code as "cid:image-name"
        message.addInline("background", new ClassPathResource(BACKGROUND_IMAGE), PNG_MIME);
        message.addInline("logo-background", new ClassPathResource(LOGO_BACKGROUND_IMAGE), PNG_MIME);
        message.addInline("thymeleaf-banner", new ClassPathResource(THYMELEAF_BANNER_IMAGE), PNG_MIME);
        message.addInline("thymeleaf-logo", new ClassPathResource(THYMELEAF_LOGO_IMAGE), PNG_MIME);

        // Send mail
        this.mailSender.send(mimeMessage);
    }
    
    public void sendActivationMail(final Integer  userId, final Locale locale) throws MessagingException {
		Runnable runner = new Runnable() {

			@Override
			public void run() {
				User user = accountService.findById(userId);
				try {
					
					log.info("Invio mail di attivazione su  "+user.getUemail()+" ");
					String uuid = UUID.randomUUID().toString();
					user.setUcode01(uuid);
					// Prepare the evaluation context
					final Context ctx = new Context(locale);
					ctx.setVariable("email", user.getUemail());
					ctx.setVariable("token", uuid);
					ctx.setVariable("server_url", serverRoot + "/activateAccount?");
					// Prepare message using a Spring helper
					final MimeMessage mimeMessage = mailSender.createMimeMessage();
					final MimeMessageHelper message = new MimeMessageHelper(mimeMessage, "UTF-8");
					message.setSubject(messageSource.getMessage("account.emai.subject", null , locale));
					message.setFrom(fromEmail);
					message.setTo(user.getUemail());
					if (!AppUtil.isEmpty(bcc)) {
						message.setBcc(bcc);
					}
					
					// Create the HTML body using Thymeleaf
					final String htmlContent = htmlTemplateEngine.process("confirm_email.html", ctx);
					message.setText(htmlContent, true /* isHtml */);
					// Send email
					mailSender.send(mimeMessage);
					
					accountService.save(user, false);
					log.info("Mail di attivazione su  "+user.getUemail()+" Inviata con successo");
				} catch (Exception e) {
					user.setUcode02(Const.ERR_SEND_MAIL);
					accountService.save(user, false);
					log.warn("Mail di attivazione su  "+user.getUemail()+" ERRORE");
					log.error(e.getMessage(),e);
				}
			}

		};
		Thread t = new Thread(runner);
		t.start();

	}


	public void sendCustomerActivationMail(Integer cuid, Locale locale) {
		Runnable runner = new Runnable() {

			@Override
			public void run() {
				Customer user = custoRep.findById(cuid);
				try {
					
					log.info("Invio mail di attivazione su  "+user.getCuusername()+" ");
					String uuid = UUID.randomUUID().toString();
					user.setCucode01(uuid);
					// Prepare the evaluation context
					final Context ctx = new Context(locale);
					ctx.setVariable("email", user.getCuusername());
					ctx.setVariable("token", uuid);
					ctx.setVariable("server_url", serverRoot + "/activateAccount?");
					// Prepare message using a Spring helper
					final MimeMessage mimeMessage = mailSender.createMimeMessage();
					final MimeMessageHelper message = new MimeMessageHelper(mimeMessage, "UTF-8");
					message.setSubject(messageSource.getMessage("account.emai.subject", null , locale));
					message.setFrom(fromEmail);
					message.setTo(user.getCuusername());
					if (!AppUtil.isEmpty(bcc)) {
						message.setBcc(bcc);
					}
					
					// Create the HTML body using Thymeleaf
					final String htmlContent = htmlTemplateEngine.process("confirm_email.html", ctx);
					message.setText(htmlContent, true /* isHtml */);
					// Send email
					mailSender.send(mimeMessage);
					
					custoRep.store(user);
					log.info("Mail di attivazione su  "+user.getCuusername()+" Inviata con successo");
				} catch (Exception e) {
					user.setCucode02(Const.ERR_SEND_MAIL);
					custoRep.store(user);
					log.warn("Mail di attivazione su  "+user.getCuusername()+" ERRORE");
					log.error(e.getMessage(),e);
				}
			}

		};
		Thread t = new Thread(runner);
		t.start();
		
	}

}
