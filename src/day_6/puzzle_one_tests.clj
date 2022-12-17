(ns day-6.puzzle-one-tests
  (:require [day-6.puzzle-one :as sut]
            [clojure.test :refer [deftest is testing]]
            [clojure.java.io :as io]))

(deftest find-start-of-packet-marker
  (testing "Example input"
    (is (= (sut/start-of-packet-marker "bvwbjplbgvbhsrlpgdmjqwftvncz") 5))
    (is (= (sut/start-of-packet-marker "nppdvjthqldpwncqszvftbrmjlhg") 6))
    (is (= (sut/start-of-packet-marker "nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg") 10))
    (is (= (sut/start-of-packet-marker "zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw") 11)))

  (testing "User specific input"
    (let [input (slurp (io/resource "day_6/input.txt"))]
      (is (= (sut/start-of-packet-marker input) 1034)))))
