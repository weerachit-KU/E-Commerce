package Lib;

import java.util.ArrayList;

public class ShoppingCartManualTest {

    public static void run() {
        System.out.println("--- Starting Shopping Cart Calculator Tests ---");
        System.out.println(); // for spacing

        int passedCount = 0;
        int failedCount = 0;

        // Test 1: ตะกร้าเป็น null
        try {
            double test1 = ShoppingCartCalculator.calculateTotalPrice(null);
            if (test1 == 0.0) {
                System.out.println("TEST1 PASSED: Null cart should return 0.0");
                passedCount++;
            } else {
                System.out.println("FAILED: Null cart expected 0.0 but got " + test1);
                failedCount++;
            }
        } catch (Exception e) {
            System.out.println("FAILED: Null cart caused an exception: " + e.getMessage());
            failedCount++;
        }

        // Test 2: ตะกร้าว่าง
        ArrayList<CartItem> emptyCart = new ArrayList<>();
        double test2 = ShoppingCartCalculator.calculateTotalPrice(emptyCart);
        if (test2 == 0.0) {
            System.out.println("TEST2 PASSED: Empty cart should return 0.0");
            passedCount++;
        } else {
            System.out.println("FAILED: Empty cart expected 0.0 but got " + test2);
            failedCount++;
        }

        // Test 3: คำนวณปกติ ไม่มีส่วนลด
        ArrayList<CartItem> simpleCart = new ArrayList<>();
        simpleCart.add(new CartItem("NORMAL", "Bread", 25.0, 2)); // 50
        simpleCart.add(new CartItem("NORMAL", "Milk", 15.0, 2)); // 30
        double test3 = ShoppingCartCalculator.calculateTotalPrice(simpleCart);
        if (test3 == 80.0) {
            System.out.println("TEST3 PASSED: Simple cart total is correct (65.0)");
            passedCount++;
        } else {
            System.out.println("FAILED: Simple cart total expected 65.0 but got " + test3);
            failedCount++;
        }

        // Test 4: ส่วนลด BOGO
        ArrayList<CartItem> bogoCart = new ArrayList<>();
        bogoCart.add(new CartItem("BOGO", "Eggs", 30.0, 3)); // 60, buy 1 get 1 free
        double test4 = ShoppingCartCalculator.calculateTotalPrice(bogoCart);
        if (test4 == 60.0) {
            System.out.println("TEST4 PASSED: BOGO cart total is correct (60.0)");
            passedCount++;
        } else {
            System.out.println("FAILED: BOGO cart total expected 60.0 but got " + test4);
            failedCount++;
        }
        // Test 5: ส่วนลดBulk
        ArrayList<CartItem> bulkCart = new ArrayList<>();
        bulkCart.add(new CartItem("BULK", "Milk", 15.0, 6));
        double test5 = ShoppingCartCalculator.calculateTotalPrice(bulkCart);
        if (test5 == 81.0) {
            System.out.println("TEST5 PASSED: Bulk cart total is correct (81.0)");
            passedCount++;
        } else {
            System.out.println("FAILED: Bulk cart total expected 81.0 but got " + test5);
            failedCount++;
        }

        // Test 6: quantity และ price ติดลบ
        ArrayList<CartItem> negativeValue = new ArrayList<>();
        negativeValue.add(new CartItem("NORMAL", "Eggs", -20.0, 1));
        negativeValue.add(new CartItem("NORMAL", "Bread", 25.0, -1));
        double test6 = ShoppingCartCalculator.calculateTotalPrice(negativeValue);
        if (test6 == 0.0) {
            System.out.println("TEST6PASSED: Negative value cart total is correct (0.0)");
            passedCount++;
        } else {
            System.out.println("FAILED: Negative value cart total expected 0.0 but got " + test6);
            failedCount++;
        }

        // Test 7: item หลายประเภท with some conflict???
        ArrayList<CartItem> multiitem = new ArrayList<>();
        multiitem.add(new CartItem("BULK", "Milk", 15.0, 6)); // 81.0
        multiitem.add(new CartItem("BOGO", "Eggs", 30.0, 3)); // 60.0
        multiitem.add(new CartItem("NORMAL", "Bread", 25.0, 2)); // 50.0
        multiitem.add(new CartItem("NORMAL", "Eggs", -20.0, 1)); // 0.0
        multiitem.add(new CartItem("NORMAL", "Bread", 25.0, -1)); // 0.0
        double test7 = ShoppingCartCalculator.calculateTotalPrice(multiitem);
        if (test7 == 191.0) {
            System.out.println("TEST7 PASSED: Multiple item cart total is correct (191.0)");
            passedCount++;
        } else {
            System.out.println("FAILED: Multiple item cart total expected 191.0 but got " + test7);
            failedCount++;
        }

        // --- Test Summary ---
        System.out.println("\n--------------------");
        System.out.println("--- Test Summary ---");
        System.out.println("Passed: " + passedCount + ", Failed: " + failedCount);
        if (failedCount == 0) {
            System.out.println("Excellent! All tests passed!");
        } else {
            System.out.println("Some tests failed.");
        }
    }
}