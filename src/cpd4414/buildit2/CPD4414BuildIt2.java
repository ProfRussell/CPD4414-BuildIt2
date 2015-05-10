/*
 * Copyright 2015 Len Payne <len.payne@lambtoncollege.ca>.
 * Updated 2015 Mark Russell <mark.russell@lambtoncollege.ca>.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cpd4414.buildit2;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.ArrayBlockingQueue;

/**
 *
 * @author Len Payne <len.payne@lambtoncollege.ca>
 */
public class CPD4414BuildIt2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("\nExample One\n===========");
        doExample1();
        System.out.println("\nBuildIt One\n===========");
        doBuildIt1();
        System.out.println("\nExample Two\n===========");
        doExample2();
        System.out.println("\nBuildIt Two\n===========");
        doBuildIt2();
    }

    /**
     * Example One - Using Queues
     */
    public static void doExample1() {
        Queue<String> qNames = new ArrayBlockingQueue<>(10);

        qNames.add("Bob");
        qNames.add("Lin");
        qNames.add("Ranbir");
        qNames.add("Pink");

        System.out.println("Names After Addition");
        for (String s : qNames) {
            System.out.println(s);
        }

        System.out.println("\nRemoving Name at Top of Queue: " + qNames.remove());

        System.out.println("\nNames After Removal");
        for (String s : qNames) {
            System.out.println(s);
        }
    }

    /**
     * Build It! One - HashSet
     */
    public static void doBuildIt1() {
        HashSet<String> hNames = new HashSet<>();
        hNames.add("Phil");
        hNames.add("Judy");
        hNames.add("Lade");
        hNames.add("Ziggy");

        System.out.println("Is Phil there? " + hNames.contains("Phil"));
        System.out.println("Is Judy there? " + hNames.contains("Judy"));
        System.out.println("Is Lade there? " + hNames.contains("Lade"));
        System.out.println("Is Ziggy there? " + hNames.contains("Ziggy"));

        hNames.remove("Judy");
        System.out.println("\nIs Judy still there after removing? " + hNames.contains("Judy"));
    }

    /**
     * Customer Class for "Real World" Example Two
     */
    private static class Customer {

        private List<String> toDoList = new ArrayList<>();
        private String accountId;

        public void addToDo(String s) {
            toDoList.add(s);
        }

        public List<String> getToDoList() {
            return toDoList;
        }

        public void setToDoList(List<String> toDoList) {
            this.toDoList = toDoList;
        }

        public String getAccountId() {
            return accountId;
        }

        public void setAccountId(String accountId) {
            this.accountId = accountId;
        }

    }

    /**
     * Account Class for "Real World" Example Two
     */
    private static class Account {

        List<String> purchaseHistory = new ArrayList<>();

        public List<String> getPurchaseHistory() {
            return purchaseHistory;
        }

        public void setPurchaseHistory(List<String> purchaseHistory) {
            this.purchaseHistory = purchaseHistory;
        }

        public void addPurchase(String s) {
            purchaseHistory.add(s);
        }
    }

    /**
     * Example Two - "Real World"
     */
    public static void doExample2() {
        // Set up the Environment (waiting room and account storage)
        Queue<Customer> waitingRoom = new ArrayDeque<Customer>();
        Map<String, Account> accounts = new TreeMap<>();

        // Set up Bob - a New Customer
        Customer bob = new Customer();
        bob.setAccountId("ABC123");
        bob.addToDo("Complain loudly.");
        bob.addToDo("Tell a random story about cat.");
        bob.addToDo("Purchase 1 blanket.");
        bob.addToDo("Complain loudly again.");
        // Set up Bob's New Account
        accounts.put("ABC123", new Account());
        // Put Bob in the Waiting Room
        waitingRoom.add(bob);

        // Set up Jolene - a Returning Customer
        Customer jolene = new Customer();
        jolene.setAccountId("CBC333");
        jolene.addToDo("Purchase 2 blankets.");
        // Set up Jolene's Account History
        Account jolenesAccount = new Account();
        jolenesAccount.addPurchase("Purchase 1 sheet set.");
        accounts.put("CBC333", jolenesAccount);
        // Put Jolene in the Waiting Room
        waitingRoom.add(jolene);

        // Iterate through the people in the waiting room
        while (!waitingRoom.isEmpty()) {
            // Removes the next person from the Waiting Room
            Customer customer = waitingRoom.remove();
            String customerId = customer.getAccountId();
            System.out.printf("Helping Customer ID: %s\n", customerId);
            // Iterate through the to-do list of each customer
            for (String toDo : customer.getToDoList()) {
                // If the customer makes a purchase, add it to their history
                if (toDo.contains("Purchase")) {
                    Account customerAccount = accounts.get(customerId);
                    customerAccount.addPurchase(toDo);
                    System.out.printf("Added \"%s\" to account history.\n", toDo);
                }
            }
        }
    }

    /**
     * Build It! Two - Queues of Maps of Sets
     */
    public static void doBuildIt2() {
        // Set up the Initial Queue
        Queue<Map> queue = new ArrayDeque<>();
        
        // Add the first Map to it
        queue.add(new TreeMap<>());
        
        // Map a String to a Set
        Set inputs = new HashSet<>();
        inputs.add(1);
        inputs.add(3);
        inputs.add(4);
        queue.element().put("inputs", inputs);
        
        // Map another String to another Set
        Set outputs = new HashSet<>();
        outputs.add(1);
        outputs.add(2);
        queue.element().put("outputs", outputs);
        
        // Iterate through the Map at the End
        while (!queue.isEmpty()) {
            Map m = queue.remove();
            for (Object k : m.keySet()) {
                System.out.println(k);
                System.out.println(m.get(k));
            }
        }
    }
}
