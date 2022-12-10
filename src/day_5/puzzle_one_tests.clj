(ns day-5.puzzle-one-tests
  (:require [day-5.puzzle-one :as sut]
            [clojure.test :refer [deftest is testing]]
            [clojure.java.io :as io]))

(deftest can-parse-initial-cargo-stacks
  (let [expected {1 [\S \L \W]
                  2 [\J \T \N \Q]
                  3 [\S \C \H \F \J]
                  4 [\T \R \M \W \N \G \B]
                  5 [\T \R \L \S \D \H \Q \B]
                  6 [\M \J \B \V \F \H \R \L]
                  7 [\D \W \R \N \J \M]
                  8 [\B \Z \T \F \H \N \D \J]
                  9 [\H \L \Q \N \B \F \T]}
        input "                [B] [L]     [J]
            [B] [Q] [R]     [D] [T]
            [G] [H] [H] [M] [N] [F]
        [J] [N] [D] [F] [J] [H] [B]
    [Q] [F] [W] [S] [V] [N] [F] [N]
[W] [N] [H] [M] [L] [B] [R] [T] [Q]
[L] [T] [C] [R] [R] [J] [W] [Z] [L]
[S] [J] [S] [T] [T] [M] [D] [B] [H]
 1   2   3   4   5   6   7   8   9 "]
    (is (= expected (sut/parse-cargo-stacks input)))))

(deftest can-parse-rearrangement-procedure
  (is (= [1 2 1] (sut/parse-rearrangement-procedure "move 1 from 2 to 1")))
  (is (= [3 1 3] (sut/parse-rearrangement-procedure "move 3 from 1 to 3")))
  (is (= [2 2 1] (sut/parse-rearrangement-procedure "move 2 from 2 to 1")))
  (is (= [1 1 2] (sut/parse-rearrangement-procedure "move 1 from 1 to 2"))))

(deftest can-move-between-cargo-stacks
  (let [initial-stacks {1 [\S \L \W]
                        2 [\J \T \N \Q]
                        3 [\S \C \H \F \J]
                        4 [\T \R \M \W \N \G \B]
                        5 [\T \R \L \S \D \H \Q \B]
                        6 [\M \J \B \V \F \H \R \L]
                        7 [\D \W \R \N \J \M]
                        8 [\B \Z \T \F \H \N \D \J]
                        9 [\H \L \Q \N \B \F \T]}]

    (testing "move 1 from 2 to 1"
      (let [expected {1 [\S \L \W \Q]
                      2 [\J \T \N]
                      3 [\S \C \H \F \J]
                      4 [\T \R \M \W \N \G \B]
                      5 [\T \R \L \S \D \H \Q \B]
                      6 [\M \J \B \V \F \H \R \L]
                      7 [\D \W \R \N \J \M]
                      8 [\B \Z \T \F \H \N \D \J]
                      9 [\H \L \Q \N \B \F \T]}
            input [1 2 1]]
        (is (= expected (sut/move-cargo-stacks initial-stacks input)))))

    (testing "move 3 from 1 to 3"
      (let [expected  {1 []
                       2 [\J \T \N \Q]
                       3 [\S \C \H \F \J \W \L \S]
                       4 [\T \R \M \W \N \G \B]
                       5 [\T \R \L \S \D \H \Q \B]
                       6 [\M \J \B \V \F \H \R \L]
                       7 [\D \W \R \N \J \M]
                       8 [\B \Z \T \F \H \N \D \J]
                       9 [\H \L \Q \N \B \F \T]}
            input [3 1 3]]
        (is (= expected (sut/move-cargo-stacks initial-stacks input)))))

    (testing "move 2 from 2 to 1"
      (let [expected  {1 [\S \L \W \Q \N]
                       2 [\J \T]
                       3 [\S \C \H \F \J]
                       4 [\T \R \M \W \N \G \B]
                       5 [\T \R \L \S \D \H \Q \B]
                       6 [\M \J \B \V \F \H \R \L]
                       7 [\D \W \R \N \J \M]
                       8 [\B \Z \T \F \H \N \D \J]
                       9 [\H \L \Q \N \B \F \T]}
            input [2 2 1]]
        (is (= expected (sut/move-cargo-stacks initial-stacks input)))))))

(deftest can-get-the-top-cargo-after-being-moved-around
  (let [expected "RLFNRTNFB"
        initial-stacks "                [B] [L]     [J]
            [B] [Q] [R]     [D] [T]
            [G] [H] [H] [M] [N] [F]
        [J] [N] [D] [F] [J] [H] [B]
    [Q] [F] [W] [S] [V] [N] [F] [N]
[W] [N] [H] [M] [L] [B] [R] [T] [Q]
[L] [T] [C] [R] [R] [J] [W] [Z] [L]
[S] [J] [S] [T] [T] [M] [D] [B] [H]
 1   2   3   4   5   6   7   8   9 "
        move-commands (slurp (io/resource "day_5/input.txt"))]
    (is (= expected (sut/supply-stacks-top-cargo initial-stacks move-commands)))))
