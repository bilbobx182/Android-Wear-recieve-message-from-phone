Objective : Transmit a string from a phone to a wear device.

Reason: MessageAPI was deprecated recently in Android. Many tutorials were based upon this older API. It was hard to find a simplified working example.

How it works:
  0) wear.xml is placed in res/values for both wear and mobile. This has the capability.
  
  Mobile
  ______________
  1) This capability is detected programatically.
  2) Best node gets chosen for the capability.
  3) requestTranscription() performs sending. Lambda there to check if it works or not.
  
  Wear
  1) Implement the interface ( implements  MessageClient.OnMessageReceivedListener )
  2) Create a listener ( Wearable.getMessageClient(this).addListener(this); )
  3) Create the onMessageRecieved(MessageEvent event) method.

Tested with : 
  Wear : Huawei Watch 2
  Phone : Nokia 3


Useful Doccumentation used while figuring out how it works:
  https://developer.android.com/training/wearables/data-layer/events.html#Listen
  https://developer.android.com/training/wearables/data-layer/data-items.html
  
