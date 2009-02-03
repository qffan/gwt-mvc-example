/**
 * Copyright 2008-2009 by Stoned Robin
 */
package com.stonedrobin.server;

import junit.framework.TestCase;

import org.apache.commons.httpclient.NameValuePair;

public class CredentialsCodecTest extends TestCase {

  public void test() {
    String[][] inputs = {
        {"Alladin", "open sesame"},
        {"A||adin", "open sesame"},
        {"A|adin", "open sesame"},
        {"|Alladin", "open sesame"},
        {"Alladin|", "open sesame"},
        {"||Alladin", "open sesame"},
        {"Alladin||", "open sesame"},
        {"Alladin", "|open sesame"},
        {"Alladin", "open sesame|"},
        {"Alladin", "||open sesame"},
        {"Alladin", "open sesame||"},
        {"A | adin", "open sesame||"},
        {"A || adin", "open sesame||"},
        {"Alladin|", "|open sesame"},
        {"Alladin |", "| open sesame"},
        {"Alladin | ", "open sesame"},
        {"", "open sesame"},
        {"Alladin", ""},
    };

    for (String[] input : inputs) {

      CredentialsCodec codec = new CredentialsCodec();

      String user = input[0];
      String password = input[1];
      
      String encoded = codec.encode(user, password);

      NameValuePair pair = codec.decode(encoded);

      String context = user + " = " + password;
      assertEquals(context, user, pair.getName());
      assertEquals(context, password, pair.getValue());
    }
  }

  public void testBadDecodes() {
    String[] inputs = {"", "abc", "ab | cd | ef", "a|b | c"};

    for (String input : inputs) {
      CredentialsCodec codec = new CredentialsCodec();

      try {
        codec.decode(input);
      } catch (Throwable e) {
        fail(input + ": must not blow up");
      }
    }
  }
}
