package com.supu.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import java.util.Date;
import java.util.UUID;

@Entity
@NamedQueries({
        @NamedQuery(name="HighAlerts.findHigh",query="SELECT a FROM HighAlerts a where ((hour(timediff(a.timestamp,utc_timestamp()))*60)+(minute(timediff(a.timestamp,utc_timestamp())))) < 120 ORDER BY a.count desc"),
        @NamedQuery(name="HighAlerts.findByVin",query="SELECT a FROM HighAlerts a WHERE a.vin=:paramVin")
})
public class HighAlerts {

    @Id
    private String vin;
    private int count;
    private Date timestamp;

    public HighAlerts(){
        this.timestamp = new Date();
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
}
