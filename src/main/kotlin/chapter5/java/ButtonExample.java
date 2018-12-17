package chapter5.java;

public class ButtonExample {

    // this is the 'traditional' Java way
    public static void main(String args[]) {
        Button button = new Button();
        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // onClick implementation
            }
        });
    }
}
