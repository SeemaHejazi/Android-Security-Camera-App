# Android-Security-Camera-App

Photosafe Security Systems is our solution for house invasions and theft through obtaining photographic evidence. 
The proposed project is to create a security camera which takes a photo when motion is detected, and sends the photo 
to the user’s Android Application. The IOT device used for this project is the Raspberry Pi 3, which has a rangefinder 
sensor and webcam attached. As the rangefinder detects motion, the webcam will be signaled to snap a photo. 
The image data will then be sent to our cloud application from which our Android application will receive a push 
notification notifying the user that a new entry was recorded and that the photo is available for them to view. 

<img width="698" alt="screen shot 2018-09-12 at 8 28 59 pm" src="https://user-images.githubusercontent.com/10931672/45460317-9c7eba00-b6ca-11e8-8dc1-e8facaa967ea.png">

Whenever a new photo model is created in the API, a notification will be sent to the android application associated with 
the particular user that corresponds to the photographed event. Whenever the app is opened by the user, it will perform a 
GET request to /api/users/<userno> which will return a list of all the photo objects created by the specific user.
The notification is sent to the user’s device using Firebase Cloud Messaging. Firebase Cloud Messaging is a product by 
Google that allows you to send notifications to both Android devices and iOS devices. It cuts down a lot of work for the 
developer in the code implementation - in particular, the developer only needs to provide a few details to Firebase, including
the device ID to address the particular device, and their Firebase API key.

## Description of method of communication with the Cloud Service. 

The end data application makes use of the Firebase Cloud Messaging solution. The server side consists of the FCM connection 
servers that are provided by Google, and the app server which we have implemented in our environment. In this system, the 
app server sends data to the client app using the HTTP protocol. The client app we created uses the FCM API, and extended the 
FirebaseMessagingService class, and the FirebaseInstanceIdService class. The FirebaseInstanceId class provides a unique 
registration token for each app instance. After being set initially, the token should remain stable unless the security of 
the previous token has been compromised, in which case it is regenerated and stored. The actual message handling is in our 
ReceivePushService class in which we chose to override the FirebaseMessagingService.onMessageReceived method, to perform 
actions based on the received RemoteMessage object and extract the message data.

## Description of communication protocol and capabilities 
Our system uses the HTTP connection server protocol rather than the XMPP connection server protocol for the following reasons: 
HTTP offers downstream messaging only, this means the messaging is one-directional, from the cloud to the device. The 
application does not require any communication from the device back to the cloud, so the use of bi-directional message 
streaming offered by XMPP was unnecessary. 
HTTP offers synchronous messaging, which means that the app server is blocked from sending a second message until a response 
to the HTTP POST request is received about the first message. 
HTTP sends both JSON messages and Plain Text messages as HTTP POST, but multicast downstream sending to multiple registration
tokens is only supported in JSON message format. On the other hand, XMPP does not support Plain Text messages or multicast 
downstream sending at all.

## Description of any data processing done.
We chose to use a type-safe REST client called Retrofit by Square, which provides a framework for authenticating and 
interacting with APIs, and sending and receiving HTTP-based network requests using OkHttp. Retrofit relies on OkHttp for the 
network library and the Gson library to serialize and deserialize API-based responses. Our interface class, PhotoAPI, defines 
the endpoint with an HTTP method, @POST. To submit form-encoded data, we use the @FormUrlEncoded annotation and the @Field to 
denote what actual payload will be submitted as form data. 
Assuming the request is not cancelled and there are no connectivity issues, the onResponse() method will be fired. It passes a 
Response object that can is used to check the status code, the response body, and any headers that were returned. Calling 
isSuccessful() for instance if the code returned a status code of 200 (or higher but less than 300). 

Note that the FCM rejects the request if the  The HTTP response contains a non-200 status code, such as 400, 401 or 500. 
The string() method on the response body loads the entire data into memory. 
Finally, when the response is successful, we use the Picasso library by Square to load the remote images into an ImageView, 
which allows it to be displayed in the application. 


Code snippets and example

HTTP POST request sample: 
```
        https://fcm.googleapis.com/fcm/send
        Content-Type:application/json
        Authorization:key=AIzaSyZ-1u...0GBYzPu7Udno5aA
        { "data": {
            "score": "5x1",
            "time": "15:10"
          },
          "to" : "bk3RNwTe3H0:CI2k_HHwgIpoDKCIZvvDMExUdFQ3P1..."
        }
```

HTTP response sample:

```
        { "multicast_id": 108,
          "success": 1,
          "failure": 0,
          "canonical_ids": 0,
          "results": [{ "message_id": "1:08" }]}
```

PhotoAPI interface: 
```
    public interface PhotoAPI {
           String ENDPOINT = "https://keithweaver.ca/posts/35/";
           @FormUrlEncoded
           @POST("/load-photos")
           void getPhotos(@Field("accesstoken") String accessToken, Callback<Res_LoadPhotos> callback);
        }
        RestAdapter builder within setupClient:
        RestAdapter.Builder builder = new RestAdapter.Builder();
                builder.setEndpoint(PhotoAPI.ENDPOINT);
                builder.setClient(new OkClient(getHttpClient()));
                builder.setLogLevel(RestAdapter.LogLevel.FULL);
                builder.setConverter(new GsonConverter(gson));


                RestAdapter restAdapter = builder.build();
                sSIClient = restAdapter.create(PhotoAPI.class);
```
ImageAdapter:

```
        ImageItem currImage = mImages.get(i);
        //Render image using Picasso library

        Picasso.with(mContext).load(currImage.getImage()).error(R.drawable.star).into(viewHolder.getMainImage());

        //Setting text view title
        String takenOn = "Date of Photo: " + currImage.getCreated().substring(0,10) + " Time: " + currImage.getCreated().substring(14,19);
        viewHolder.textView.setText(takenOn);
```

