package Entity;

import java.util.ArrayList;
import java.util.List;

public class SetCommentListEntity {

private List<CommentEntity> CommentList = new ArrayList<CommentEntity>();
    public SetCommentListEntity(){}
    
	public SetCommentListEntity(List<CommentEntity> CommentList){
		this.CommentList = CommentList;
	}
	
	public int getSize(){
		return CommentList.size();
	}
	
	public CommentEntity getItem(int arg0){
		return CommentList.get(arg0);
	}
}
