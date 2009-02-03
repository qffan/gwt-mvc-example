/**
 * Copyright 2008-2009 by Stoned Robin
 */
package com.stonedrobin.server;

import org.apache.commons.httpclient.NameValuePair;

/**
 * Encodes and decodes user credentials
 *
 * @author catalind@google.com (Catalin Dunu)
 */
public class CredentialsCodec {

  private static final char SEP = '|';

  public String encode(String user, String password) {
    return escape(user) + " | " + escape(password);
  }

  private String escape(String str) {
    return str.replace(Character.toString(SEP), "||");
  }

  public NameValuePair decode(String encoded) {
    String[] pair = encoded.split(" \\| ");
    return new NameValuePair(unescape(pair[0]), unescape(pair.length > 1 ? pair[1] : ""));
  }

  private String unescape(String str) {
    return str.replace("||", Character.toString(SEP));
  }
}
