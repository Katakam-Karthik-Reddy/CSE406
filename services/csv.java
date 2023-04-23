package org.example.services;

import com.opencsv.CSVWriter;
import org.example.automation.flight;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class csv {

    public static boolean savecsv(String path, ArrayList<ArrayList<flight>> data){

        boolean flag = false;

        File file = new File(path);
        try{
            FileWriter outputfile = new FileWriter(file);
            CSVWriter writer = new CSVWriter(outputfile);
            String[] headers = {"FlightName", "FlightNumber", "StartTime", "EndTime", "TotalJourneyTime", "TotalStops","Price"};
            writer.writeNext(headers);

            for(int i =0;i<data.size();i++) {
                ArrayList<flight> curlist = data.get(i);
                if(i==0) {
                    writer.writeNext(new String[]{"Easymytrip"});
                }
                else{
                    writer.writeNext(new String[]{"Ixigo"});
                }
                for(flight curflight:curlist) {
                    writer.writeNext(new String[]{curflight.flightname, curflight.flightnumber+"", curflight.starttime, curflight.endtime, curflight.totaljourneytime, curflight.totalstops, curflight.price});
                }
            }
            flag = true;
            writer.close();
        } catch (IOException e) {
            return false;
        }

        return flag;
    }
}
