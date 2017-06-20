package Entity;

import java.util.ArrayList;
import java.util.List;

public class SetTripNavigationTrackListEntity {

private List<TripNavigationTrackEntity> TripNavigationTrackList = new ArrayList<TripNavigationTrackEntity>();
	
	public SetTripNavigationTrackListEntity(List<TripNavigationTrackEntity> TripNavigationTrackList){
		this.TripNavigationTrackList = TripNavigationTrackList;
	}
	
	public int getSize(){
		return TripNavigationTrackList.size();
	}
	
	public TripNavigationTrackEntity getItem(int arg0){
		return TripNavigationTrackList.get(arg0);
	}

	public List<TripNavigationTrackEntity> getTripNavigationTrackList() {
		return TripNavigationTrackList;
	}
	
}
