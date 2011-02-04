/*
 * Copyright (c) 2009 Carl Sziebert
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 *
 * $Id: BCryptPasswordEncoder.java 716 2009-05-10 04:02:56Z carl $
 */
package org.codehaus.groovy.grails.plugins.springsecurity.bcrypt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.security.authentication.encoding.PasswordEncoder;

/**
 * <code>BCryptPasswordEncoder</code> is a simple <code>PasswordEncoder</code> implementation
 * that utilizes the jBCrypt library to hash user passwords for Spring Security 2.x.
 *
 * jBCrypt: http://www.mindrot.org/projects/jBCrypt/
 */
public class BCryptPasswordEncoder implements PasswordEncoder {

    private static final Logger logger = LoggerFactory.getLogger(BCryptPasswordEncoder.class);

    /**
     * Hash the supplied password using jBCrypt.
     *
     * @param rawPass <p>the user supplied plain text password</p>
     * @param salt <p>ignored, uses a random salt instead</p>
     * @return the hashed password
     * @throws DataAccessException
     */
    public String encodePassword(String rawPass, Object salt) throws DataAccessException {
        Integer logRounds = BCryptUtils.getLogRounds();
        logger.debug("Encoding password. (log rounds: " + logRounds.toString() + ")");
        return BCrypt.hashpw(rawPass, BCrypt.gensalt(logRounds));
    }

    /**
     * Check the validity of the supplied password.
     *
     * @param encPass <p>the hashed password stored in the database</p>
     * @param rawPass <p>the user supplied plain text password</p>
     * @param salt <p>ignored</p>
     * @return true if the passwords match, false otherwise
     * @throws DataAccessException
     */
    public boolean isPasswordValid(String encPass, String rawPass, Object salt) throws DataAccessException {
        logger.debug("Validating password.");
        return BCrypt.checkpw(rawPass, encPass);
    }
}