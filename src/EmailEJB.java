/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bc9959;

import java.io.*;
import java.util.*;
import javax.annotation.Resource;
import javax.ejb.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.mail.Flags.Flag;

/**
 *
 * @author NAVNEET
 */
@Stateless
public class EmailEJB implements Email {

    @Resource(name = "mail/JamesMailSession")
    private Session session;
    private static final String mailer = "JavaMailer";
    private Store store = null;
    private Folder folder = null;
    private Message[] messages = null;
    private String from;
    private String sub;
    private Date dat;
    private String con;
    private int num;

    public EmailEJB() {
    }

    public void sendMessage(String recipient, String sender, String subject,
            String data) {
        try {
            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(sender, false));
            msg.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(recipient, false));
            msg.setSubject(subject);
            Date timeStamp = new Date();
            msg.setText(data);
            msg.setHeader("X-Mailer", mailer);
            msg.setSentDate(timeStamp);
            Transport.send(msg);
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }

    @Override
    public String getMessage(int i) {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        try {
            if (i < messages.length) {
                messages[i].writeTo(buffer);
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return buffer.toString();
    }

    @Override
    public String getFromAddress(int i) {
        try {
            if (i < messages.length) {
                from = messages[i].getFrom()[0].toString();
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return from;
    }

    @Override
    public String getSub(int i) {
        try {
            if (i < messages.length) {
                sub = messages[i].getSubject();
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return sub;
    }

    @Override
    public Date getDat(int i) {
        try {
            if (i < messages.length) {
                dat = messages[i].getSentDate();
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return dat;
    }

    @Override
    public String getCon(int i) {
        try {
            if (i < messages.length) {
                con = messages[i].getContent().toString();
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return con;
    }

    @Override
    public int getNum(int i) {
        try {
            if (i < messages.length) {
                num = messages[i].getMessageNumber();
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return num;
    }

    @Override
    public int connect(String login, String passwd) {
        int number = 0;
        try {
            store = session.getStore("pop3");
            store.connect("localhost", login, passwd);
            folder = store.getFolder("INBOX");
            folder.open(Folder.READ_WRITE);
            messages = folder.getMessages();
            number = messages.length;
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return number;
    }

    @Override
    public void remove(int i) {
        try {
            messages[i].setFlag(Flag.SEEN, true);
            messages[i].setFlag(Flags.Flag.DELETED, true);
            folder.close(true);
            store.close();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    @Override
    public void disconnect() {
        try {
            folder.close(false);
            store.close();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
}
