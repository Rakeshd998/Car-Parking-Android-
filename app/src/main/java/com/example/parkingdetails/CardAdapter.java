package com.example.parkingdetails;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.CardViewHolder> {
    String n;
    String it;
    String ou;
    String money;

    private List<CardItem> cardItems;

    public CardAdapter(List<CardItem> cardItems,String n,String it,String ou,String money) {
        this.cardItems = cardItems;
        this.n=n;
        this.it=it;
        this.ou=ou;
        this.money=money;
    }

    public static class CardViewHolder extends RecyclerView.ViewHolder {
        private TextView bn,cnn,n,e;

        public CardViewHolder(View itemView) {
            super(itemView);
            bn = itemView.findViewById(R.id.t1);
            cnn=itemView.findViewById(R.id.cn);
            n=itemView.findViewById(R.id.name);
            e=itemView.findViewById(R.id.ex);
        }
    }

    @Override
    public CardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_item, parent, false);
        return new CardViewHolder(view);
    }

   /* @Override
    public void onBindViewHolder(CardViewHolder holder, int position) {
        CardItem cardItem = cardItems.get(position);
        holder.n.setText(cardItem.getName());
        holder.cnn.setText(cardItem.cn);
        holder.bn.setText(cardItem.bname);
        holder.e.setText(cardItem.ex);
    }*/
    @SuppressLint("LongLogTag")
    @Override
    public void onBindViewHolder(CardViewHolder holder, int position) {
        CardItem cardItem = cardItems.get(position);
        holder.n.setText(cardItem.getName());
        holder.cnn.setText(cardItem.cn);
        holder.bn.setText(cardItem.bname);
        holder.e.setText(cardItem.ex);
        Log.e("CardAdapter--------------------------------------------------------------------", n);
        Log.e("CardAdapter--------------------------------------------------------------------", it);
        Log.e("CardAdapter--------------------------------------------------------------------", ou);
        Log.e("CardAdapter--------------------------------------------------------------------", money);
        // Set a click listener for the card view
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("CardAdapter--------------------------------------------------------------------", n);
                Log.e("CardAdapter--------------------------------------------------------------------", it);
                Log.e("CardAdapter--------------------------------------------------------------------", ou);
                Log.e("CardAdapter--------------------------------------------------------------------", money);
                // Card is clicked, navigate to OTP activity
                Intent intent = new Intent(v.getContext(), Otp.class);

                // Pass data to the OTP activity
                intent.putExtra("pname",n);
                intent.putExtra("intime",it);
                intent.putExtra("outtime",ou);
                intent.putExtra("money",money);
                intent.putExtra("cardNumber", cardItem.cn); // Example: Pass the card number
                intent.putExtra("phoneNumber", cardItem.ph); // Pass the phone number

                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return cardItems.size();
    }
}

