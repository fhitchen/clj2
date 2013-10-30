(ns clj2.dbtest)

(require '[clojure.java.jdbc :as jdbc])
(use '[korma db core])

(def db {:classname "oracle.jdbc.driver.OracleDriver"  ; must be in classpath
         :subprotocol "oracle"
         :delimiters ""
         :subname "thin:@localhost:1521:METRICS" 
         :user "ziglogscn"
         :password "ziglogscn"})

;(jdbc/with-connection db
;  (jdbc/with-query-results res ["SELECT count(*) FROM TUXEDO_SCAN_LOG"]
;    (doall res)))

(defdb korma-db db)

;(def orc (oracle {:make_pool? false}))

(declare tuxedo_scan_log)

(defentity tuxedo_scan_log
  (table :TUXEDO_SCAN_LOG))

;(dry-run
(select tuxedo_scan_log 
        (fields :err_msg :err_detail)
        ;(limit 10)
        (where {:application "Portal"
                :event_type [not= "NOERROR"]
                :sys_creation_date [>= (sqlfn trunc (raw "CURRENT_DATE"))]})
        )
;)


