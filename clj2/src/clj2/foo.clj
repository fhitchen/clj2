(ns clj2.foo)

(require '[clojure.data.csv :as csv]
         '[clojure.java.io :as io])

(defn mysum
  "sums column in csv file"
  [file]
  (with-open [in-file (io/reader file)]
	   (apply + (map 
               #(read-string (apply str (replace {\( "" \) "" \  ""} (nth % 6)))) 
               (rest (csv/read-csv in-file))))))

(mysum "/home/fhitchen/clojure/my-stuff/st.csv")
