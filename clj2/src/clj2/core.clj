(ns clj2.core)

(require '[clojure.data.csv :as csv]
         '[clojure.java.io :as io])
(defn foo
  "I don't do a whole lot."
  [x]
  (println x "Hello, World!"))




(defn bar
  ""
  [x]
  (with-open [in-file (io/reader "/home/fhitchen/clojure/my-stuff/st.csv")]
	   (apply + 
           (map #(read-string (apply str (replace {\( "" \) "" \  ""} 
                                                  (nth % 6)))) 
                (rest 
                  (csv/read-csv in-file))))))

