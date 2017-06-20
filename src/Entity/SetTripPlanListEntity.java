package Entity;

import java.util.ArrayList;
import java.util.List;

public class SetTripPlanListEntity {

private List<TripPlanEntity> TripPlanList = new ArrayList<TripPlanEntity>();

    public SetTripPlanListEntity(){}
    
	public SetTripPlanListEntity(List<TripPlanEntity> TripPlanList){
		this.TripPlanList = TripPlanList;
	}
	
	public int getSize(){
		return TripPlanList.size();
	}
	
	public TripPlanEntity getItem(int arg0){
		return TripPlanList.get(arg0);
	}
}
