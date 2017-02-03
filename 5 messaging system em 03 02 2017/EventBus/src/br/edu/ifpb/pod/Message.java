/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.pod;

import java.util.Objects;

/**
 *
 * @author ajp
 */
public class Message {

    private String identify;
    private String publisherId;
    private String subscriberId;
    private String text;

    public Message(String identify, String publisherId, String subscriberId, String text) {
        this.identify = identify;
        this.publisherId = publisherId;
        this.subscriberId = subscriberId;
        this.text = text;
    }

    public String getIdentify() {
        return identify;
    }

    public void setIdentify(String identify) {
        this.identify = identify;
    }

    public String getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(String publisherId) {
        this.publisherId = publisherId;
    }

    public String getSubscriberId() {
        return subscriberId;
    }

    public void setSubscriberId(String subscriberId) {
        this.subscriberId = subscriberId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Message other = (Message) obj;
        if (!Objects.equals(this.identify, other.identify)) {
            return false;
        }
        if (!Objects.equals(this.publisherId, other.publisherId)) {
            return false;
        }
        if (!Objects.equals(this.subscriberId, other.subscriberId)) {
            return false;
        }
        if (!Objects.equals(this.text, other.text)) {
            return false;
        }
        return true;
    }

}
