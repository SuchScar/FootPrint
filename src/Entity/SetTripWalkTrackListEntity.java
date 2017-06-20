package Entity;

import java.util.ArrayList;
import java.util.List;

public class SetTripWalkTrackListEntity {

private List<TripWalkTrackEntity> TripWalkTrackList = new ArrayList<TripWalkTrackEntity>();
	
	public SetTripWalkTrackListEntity(List<TripWalkTrackEntity> TripWalkTrackList){
		this.TripWalkTrackList = TripWalkTrackList;
	}
	public SetTripWalkTrackListEntity(){}
	
	
	public List<TripWalkTrackEntity> getTripWalkTrackList() {
		return TripWalkTrackList;
	}


	public int getSize(){
		return TripWalkTrackList.size();
	}
	
	public TripWalkTrackEntity getItem(int arg0){
		return TripWalkTrackList.get(arg0);
	}
}
