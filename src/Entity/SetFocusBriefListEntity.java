package Entity;

import java.util.ArrayList;
import java.util.List;

public class SetFocusBriefListEntity {

	private List<FocusBriefEntity> FocusBriefList = new ArrayList<FocusBriefEntity>();
		
		public SetFocusBriefListEntity(List<FocusBriefEntity> FocusBriefList){
			this.FocusBriefList = FocusBriefList;
		}
		
		public int getSize(){
			if(FocusBriefList==null||FocusBriefList.size()==0){
				return 0;
				}else{
			return FocusBriefList.size();}
		}
		
		public FocusBriefEntity getItem(int arg0){
			return FocusBriefList.get(arg0);
		}

		public List<FocusBriefEntity> getFocusBriefList() {
			return FocusBriefList;
		}

		public void setFocusBriefList(List<FocusBriefEntity> focusBriefList) {
			FocusBriefList = focusBriefList;
		}
		
	
}
