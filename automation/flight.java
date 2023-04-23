package org.example.automation;

public class flight {
    public String flightname;
    public String flightnumber;
    public String starttime;
    public String endtime;
    public String totaljourneytime;
    public String totalstops;
    public String price;

    public flight(String flightname, String flightnumber, String starttime, String endtime, String totaljourneytime, String totalstops, String price) {
        this.flightname = flightname;
        this.flightnumber = flightnumber;
        this.starttime = starttime;
        this.endtime = endtime;
        this.totaljourneytime = totaljourneytime;
        this.totalstops = totalstops;
        this.price = price;
    }

    @Override
    public String toString() {
        return "easymytrip [flightname=" + flightname + ", flightnumber=" + flightnumber + ", starttime=" + starttime
                + ", endtime=" + endtime + ", totaljourneytime=" + totaljourneytime + ", totalstops=" + totalstops
                + ", price=" + price + "]";
    }
}
