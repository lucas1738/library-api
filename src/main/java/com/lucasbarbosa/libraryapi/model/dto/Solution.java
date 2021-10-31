package com.lucasbarbosa.libraryapi.model.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/** @author Lucas Barbosa on 05/09/2021 */
public class Solution {

  static List<AtomicReference<Integer>> seats; // Movie seats numbered as per
  // list index

  public static void main(String[] args) throws InterruptedException {
    // TODO Auto-generated method stub
    seats = new ArrayList<>();
    for (int seatCount = 0; seatCount < 20; seatCount++) { // 20 seats
      seats.add(new AtomicReference<Integer>());
    }
    Thread[] customers = new Thread[22]; // 22 users
    for (int customerCount = 0; customerCount < customers.length; customerCount++) {
      customers[customerCount] = new Cinema(seats, customerCount);
      customers[customerCount].start();
    }
    for (Thread t : customers) {
      t.join();
    }
    for (AtomicReference<Integer> seat : seats) {
      System.out.print(" " + seat.get());
    }
  }

  /**
   * id is the id of the user
   *
   * @author sankbane
   */
  static class Cinema extends Thread { // each thread is a user
    static AtomicInteger seat = new AtomicInteger(0);
    List<AtomicReference<Integer>> seats; // seats
    int id; // id of the users
    int numberOfSeats;

    public Cinema(List<AtomicReference<Integer>> list, int userId) {
      seats = list;
      this.id = userId;
      numberOfSeats = list.size();
    }

    @Override
    public void run() {
      boolean reserved = false;
      try {
        while (!reserved && seat.get() < numberOfSeats) {
          Thread.sleep(50);
          int wantedSeat = ThreadLocalRandom.current().nextInt(0, numberOfSeats); // excludes
          // seats
          //
          AtomicReference<Integer> customerSeat = seats.get(wantedSeat);
          reserved = customerSeat.compareAndSet(null, id); // null means no user
          // has reserved this
          // seat
          if (reserved) seat.getAndIncrement();
        }
        if (!reserved && seat.get() == numberOfSeats)
          System.out.println("user " + id + " did not get a seat");
      } catch (InterruptedException ie) {
        // log it
      }
    }
  }
}
