(ns day-6.puzzle-tests
  (:require [day-6.puzzle :as sut]
            [clojure.test :refer [deftest is testing]]
            [clojure.java.io :as io]))

(deftest find-start-of-packet-marker
  (testing "Example input"
    (is (= (sut/start-of-marker 4 "bvwbjplbgvbhsrlpgdmjqwftvncz") 5))
    (is (= (sut/start-of-marker 4 "nppdvjthqldpwncqszvftbrmjlhg") 6))
    (is (= (sut/start-of-marker 4 "nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg") 10))
    (is (= (sut/start-of-marker 4 "zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw") 11)))

  (testing "User specific input"
    (let [input (slurp (io/resource "day_6/input.txt"))]
      (is (= (sut/start-of-marker 4 input) 1034)))))

(deftest find-start-of-message-marker
  (testing "Example input"
    (is (= (sut/start-of-marker 14 "mjqjpqmgbljsphdztnvjfqwrcgsmlb") 19))
    (is (= (sut/start-of-marker 14 "bvwbjplbgvbhsrlpgdmjqwftvncz") 23))
    (is (= (sut/start-of-marker 14 "nppdvjthqldpwncqszvftbrmjlhg") 23))
    (is (= (sut/start-of-marker 14 "nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg") 29))
    (is (= (sut/start-of-marker 14 "zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw") 26)))

  (testing "User specific input"
    (let [input (slurp (io/resource "day_6/input.txt"))]
      (is (= (sut/start-of-marker 14 input) 2472)))))
