package com.example.neema.storyboard;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.List;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.CommentViewHolder> {
    public List<String> comments;
    public class CommentViewHolder extends RecyclerView.ViewHolder {
        private TextView text;

        public CommentViewHolder(View view) {
            super(view);
            text = (TextView) view.findViewById(R.id.commentView);

        }
    }
    public CommentAdapter(List<String> comments) {
        this.comments = comments;
    }
    @Override
    public CommentViewHolder onCreateViewHolder(ViewGroup parent,int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.comment,parent,false);
        return new CommentViewHolder(itemView);
    }
    @Override
    public int getItemCount() {
        return comments.size();
    }
    @Override
    public void onBindViewHolder(CommentViewHolder holder, int position) {
        String comment = comments.get(position);
        holder.text.setText(comment);
        //TODO SET DATA OF THE CURRENT HOLDER

    }
}
