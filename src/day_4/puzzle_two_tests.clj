(ns day-4.puzzle-two-tests
  (:require [day-4.puzzle-two :as sut]
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

(deftest find-range-that-overlap-in-any-place
  (is (= false (sut/overlaps? [[2 3 4] [6 7 8]])))
  (is (= false (sut/overlaps? [[2 3] [4 5]])))
  (is (= true (sut/overlaps? [[5 6 7] [7 8 9]])))
  (is (= true (sut/overlaps? [[2 3 4 5 6 7 8] [3 4 5 6 7]])))
  (is (= true (sut/overlaps? [[6] [4 5 6]])))
  (is (= true (sut/overlaps? [[2 3 4 5 6] [4 5 6 7 8]]))))

(deftest find-total-assignment-pairs-that-overlap
  (testing "Example input."
    (let [input "2-4,6-8
2-3,4-5
5-7,7-9
2-8,3-7
6-6,4-6
2-6,4-8"]
      (is (= 4 (sut/camp-cleanup input)))))

  (testing "User specific input."
    (let [input (slurp (io/resource "day_4/input.txt"))]
      (is (= 931 (sut/camp-cleanup input))))))
