(ns day-2.tests
  (:require [day-2.core :as sut]
            [clojure.test :refer [deftest is testing]]
            [clojure.java.io :as io]))

(deftest shape-score
  (is (= (:rock sut/shape-score) 1))
  (is (= (:paper sut/shape-score) 2))
  (is (= (:scissors sut/shape-score) 3)))

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

(deftest parse-paragraph-lines
  (is (= (sut/parse-paragraph-line "A Y") [:rock :paper]))
  (is (= (sut/parse-paragraph-line "B X") [:paper :rock]))
  (is (= (sut/parse-paragraph-line "C Z") [:scissors :scissors])))

(deftest calculate-matchup-score
  (testing "Rock match."
    (is (= (get-in sut/matchup-score [:rock :rock]) 3))
    (is (= (get-in sut/matchup-score [:rock :paper]) 6))
    (is (= (get-in sut/matchup-score [:rock :scissors]) 0)))

  (testing "Paper match."
    (is (= (get-in sut/matchup-score [:paper :rock]) 0))
    (is (= (get-in sut/matchup-score [:paper :paper]) 3))
    (is (= (get-in sut/matchup-score [:paper :scissors]) 6)))

  (testing "Scissors match."
    (is (= (get-in sut/matchup-score [:scissors :rock]) 6))
    (is (= (get-in sut/matchup-score [:scissors :paper]) 0))
    (is (= (get-in sut/matchup-score [:scissors :scissors]) 3))))

(deftest calculate-round-score
  (is (= (sut/round-score [:rock :paper]) 8))
  (is (= (sut/round-score [:paper :rock]) 1))
  (is (= (sut/round-score [:scissors :scissors]) 6)))

(deftest calculate-total-score
  (testing "Example input."
    (is (= (sut/total-score "A Y\nB X\nC Z") 15)))

  (testing "User specific input example."
    (let [input (slurp (io/resource "day_2/input.txt"))]
      (is (= (sut/total-score input) 9651)))))
