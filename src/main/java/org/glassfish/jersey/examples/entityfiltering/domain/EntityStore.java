/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright (c) 2013-2015 Oracle and/or its affiliates. All rights reserved.
 *
 * The contents of this file are subject to the terms of either the GNU
 * General Public License Version 2 only ("GPL") or the Common Development
 * and Distribution License("CDDL") (collectively, the "License").  You
 * may not use this file except in compliance with the License.  You can
 * obtain a copy of the License at
 * http://glassfish.java.net/public/CDDL+GPL_1_1.html
 * or packager/legal/LICENSE.txt.  See the License for the specific
 * language governing permissions and limitations under the License.
 *
 * When distributing the software, include this License Header Notice in each
 * file and include the License file at packager/legal/LICENSE.txt.
 *
 * GPL Classpath Exception:
 * Oracle designates this particular file as subject to the "Classpath"
 * exception as provided by Oracle in the GPL Version 2 section of the License
 * file that accompanied this code.
 *
 * Modifications:
 * If applicable, add the following below the License Header, with the fields
 * enclosed by brackets [] replaced by your own identifying information:
 * "Portions Copyright [year] [name of copyright owner]"
 *
 * Contributor(s):
 * If you wish your version of this file to be governed by only the CDDL or
 * only the GPL Version 2, indicate your decision by adding "[Contributor]
 * elects to include this software in this distribution under the [CDDL or GPL
 * Version 2] license."  If you don't indicate a single choice of license, a
 * recipient has the option to distribute your version of this file under
 * either the CDDL, the GPL Version 2 or to extend the choice of license to
 * its licensees as provided above.  However, if you add GPL Version 2 code
 * and therefore, elected the GPL Version 2 license, then the option applies
 * only if the new code is made subject to such option by the copyright
 * holder.
 */

package org.glassfish.jersey.examples.entityfiltering.domain;


import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Entity-store utility class. Class creates a sample instance of each entity.
 *
 * @author Michal Gajdos (michal.gajdos at oracle.com)
 */
@SuppressWarnings({"JavaDoc", "UnusedDeclaration"})
public final class EntityStore {

    private static final Map<Long, User> USERS = new LinkedHashMap<>();

    static {
       
        // Users.
        final User robot = createUser("Jersey Robot", "very@secret.com");
        final User nest1 = createUser("Nested User 1", "nest@example.com");
        final User nest2 = createUser("Nested User 2", "nest2@example.com");
        final User nest3 = createUser("Nested User 3", "nest3@example.com");
        final User nest4 = createUser("Nested User 4", "nest4@example.com");

        //Add Nested Objects to Users
        populateNestedObjects();
    }
    
    public static void populateNestedObjects(){
    	
    	//Adding Nested Objects to show bug
    	for(int x=1;x < USERS.size() - 1; x++){
    		
    		USERS.get(new Long(x)).getDirectReports()
    								.add(USERS.get(new Long(x + 1)));
    	}
    	
    }

    public static User createUser(final String name, final String email) {
        return createUser(name, email, null);
    }

    public static User createUser(final String name, final String email, final List<User> directReports) {
        final User user = new User(USERS.size() + 1L, name, email);

        //user.setProjects(projects == null ? new ArrayList<Project>() : projects);
        //user.setTasks(tasks == null ? new ArrayList<Task>() : tasks);
        user.setDirectReports(directReports == null ? new ArrayList<User>() : directReports);
        USERS.put(user.getId(), user);
        

        return user;
    }

    public static User getUser(final Long id) {
        return USERS.get(id);
    }

    public static List<User> getUsers() {
    	    	
    	
        return new ArrayList<>(USERS.values());
    }

    /**
     * Prevent instantiation.
     */
    private EntityStore() {
    }
}
