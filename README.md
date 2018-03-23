Objective : Send a string from a phone to a wear device.


Why this repo exists: MessageAPI was deprecated recently in Android. Many tutorials were based upon this older API. It was hard to find a simplified working example. This repo focuses on the MessageClient Google have.

How it works:
  0) wear.xml is placed in res/values for both wear and mobile. This where the capability is set. A capability can be thought of as advertising "Hey this thing defined right here in the XML I know what it is and I can do it".
  
  Mobile
  ______________
  1) This capability is detected programatically. So that we know what capability we need to send to.
  2) We then search for the best node. A node in this case is a device, we are interested in the wear device.
  3) Once we have the node, we can then use requestTranscription() to perform sending of a message.
  
  Wear
  1) Implement the interface ( implements  MessageClient.OnMessageReceivedListener )
  2) Create a listener ( Wearable.getMessageClient(this).addListener(this); ).
  3) Create the onMessageRecieved(MessageEvent event) method. Here we can parse the message recieved that was sent by the Mobile device.

Tested with : 
  Wear : Huawei Watch 2
  Phone : Nokia 3


Useful Doccumentation used while figuring out how it works:
  https://developer.android.com/training/wearables/data-layer/events.html#Listen
  https://developer.android.com/training/wearables/data-layer/data-items.html
  
  
  Keywords that you probably found this by:
  Android wear recieve message
  Android send message from phone to android wear
  MessageAPI android wear
  
  If you have any questions feel free to ask.
