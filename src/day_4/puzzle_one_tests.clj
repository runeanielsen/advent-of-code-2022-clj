(ns day-4.puzzle-one-tests
  (:require [day-4.puzzle-one :as sut]
            [clojure.test :refer [deftest is testing]]
            [clojure.java.io :as io]))

(deftest sections-are-splitted
  (let [expected ["2-4,6-8"
                  "2-3,4-5"
                  "5-7,7-9"
                  "2-8,3-7"
                  "6-6,4-6"
                  "2-6,4-8"]
        input "2-4,6-8
2-3,4-5
5-7,7-9
2-8,3-7
6-6,4-6
2-6,4-8"]
    (is (= expected (sut/split-sections input)))))

(deftest make-sections-into-ranges []
  (is (= [[2 3 4] [6 7 8]] (sut/section-range "2-4,6-8")))
  (is (= [[2 3] [4 5]] (sut/section-range "2-3,4-5")))
  (is (= [[5 6 7] [7 8 9]] (sut/section-range "5-7,7-9")))
  (is (= [[2 3 4 5 6 7 8] [3 4 5 6 7]] (sut/section-range "2-8,3-7")))
  (is (= [[6] [4 5 6]] (sut/section-range "6-6,4-6")))
  (is (= [[2 3 4 5 6] [4 5 6 7 8]] (sut/section-range "2-6,4-8"))))

(deftest find-range-that-fully-contains-the-other
  (is (= false (sut/fully-contained? [[2 3 4] [6 7 8]])))
  (is (= false (sut/fully-contained? [[2 3] [4 5]])))
  (is (= false (sut/fully-contained? [[5 6 7] [7 8 9]])))
  (is (= true (sut/fully-contained? [[2 3 4 5 6 7 8] [3 4 5 6 7]])))
  (is (= true (sut/fully-contained? [[6] [4 5 6]])))
  (is (= false (sut/fully-contained? [[2 3 4 5 6] [4 5 6 7 8]]))))

(deftest find-total-assignment-pairs-that-overlap
  (testing "Example input."
    (let [input "2-4,6-8
2-3,4-5
5-7,7-9
2-8,3-7
6-6,4-6
2-6,4-8"]
      (is (= 2 (sut/camp-cleanup input)))))

  (testing "User specific input."
    (let [input (slurp (io/resource "day_4/input.txt"))]
      (is (= 550 (sut/camp-cleanup input))))))
