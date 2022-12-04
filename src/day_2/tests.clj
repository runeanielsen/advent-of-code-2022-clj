(ns day-2.tests
  (:require [day-2.core :as sut]
            [clojure.test :refer [deftest is testing]]
            [clojure.java.io :as io]))

(deftest shape-score-gives-correct-points
  (is (:rock sut/shape-score) 1)
  (is (:paper sut/shape-score) 2)
  (is (:scissors sut/shape-score) 3))

(deftest encrypted-shape-conversion
  (is (= (sut/encrypted-shape-conversion "X") :rock))
  (is (= (sut/encrypted-shape-conversion "Y") :paper))
  (is (= (sut/encrypted-shape-conversion "Z") :scissors)))

(deftest shape-conversion
  (is (= (sut/shape-conversion "A") :rock))
  (is (= (sut/shape-conversion "B") :paper))
  (is (= (sut/shape-conversion "C") :scissors)))

(deftest get-paragraph-lines
  (is (sut/paragraph-lines "A Y\nB X\nC Z") ["A Y" "B X" "C Z"])
  (is (sut/paragraph-lines "A Y\nC Z") ["A Y" "C Z"])
  (is (sut/paragraph-lines "A Y") ["A Y"]))

(deftest parse-paragraph-line
  (is (= (sut/parse-paragraph-line "A Y") [:rock :paper]))
  (is (= (sut/parse-paragraph-line "B X") [:paper :rock]))
  (is (= (sut/parse-paragraph-line "C Z") [:scissors :scissors])))

(deftest calculate-match-point
  (testing "Rock match."
    (is (= (sut/match-point [:rock :rock]) 3))
    (is (= (sut/match-point [:rock :paper]) 6))
    (is (= (sut/match-point [:rock :scissors]) 0)))

  (testing "Paper match."
    (is (= (sut/match-point [:paper :rock]) 0))
    (is (= (sut/match-point [:paper :paper]) 3))
    (is (= (sut/match-point [:paper :scissors]) 6)))

  (testing "Scissors match."
    (is (= (sut/match-point [:scissors :rock]) 6))
    (is (= (sut/match-point [:scissors :paper]) 0))
    (is (= (sut/match-point [:scissors :scissors]) 3))))

(deftest calculate-turn-points
  (is (= (sut/turn-points [:rock :paper]) 8))
  (is (= (sut/turn-points [:paper :rock]) 1))
  (is (= (sut/turn-points [:scissors :scissors]) 6)))

(deftest rock-paper-scissors-score-is-calculated-correctly
  (testing "Example input."
    (is (= (sut/rock-paper-scissors-score "A Y\nB X\nC Z") 15)))

  (testing "User specific input example."
    (let [input (slurp (io/resource "day_2/input.txt"))]
      (is (= (sut/rock-paper-scissors-score input) 9651)))))
