# Aim

To create an affordable SMART FAN without any remote control that works based on the principles of gesture recognition.

# Objective

To recognise fingers in the environment.

    - Applying segmentation to divide the fingers and background.
    - Applying Otsu thresholding to obtain the fingers’ outlines in image.
    - Applying high boost filter to sharpen the image.

Counting the fingers so as to change the speed of the fan from 0 to 4.

    - Cosine Angle Between the figures
    - Contours Counting
    - Event triggering

# Benefits
On looking in the present times there has been an advancement in almost every appliance or electronics we use today and the level of technological advancement is leaps and bounds. But even today when you take a closer look at the fans, we are not much evolved. Ordinary fans are still at their initial stages, for a span of many years there has been no development on it. The most recent progress that we can see in improving a normal switch enabled fan to a remote-controlled fan, which is usually a higher-end version that not everybody can afford. Although we can see that air conditioners are smart today but they are very expensive for everybody to afford. But on the other hand, we can see that fan is the cheapest cooling appliance that any person can afford. So, the requirement of today’s world is cheap viable smart fan that is technologically evolved but affordable.

# Problem Statement
The fan used in today’s world is very old fashioned and not that advance. Most human feels the inconvenient about changing the fan speed level, turning it on/off manually when he wants to change. It is very hard for specially abled people to operate an ordinary fan also. Our proposed project will be helpful in eradicating the following problems – 
- Old, aged people can easily operate it without much to remember how to use it. 
- Specially abled peoples face a lot of issues to use a fan.

Humans always look for the easiest way possible for doing a particular task. The goal of automation is to make the system more efficient and usable for all kinds of people (especially the disabled). Our app here focuses on gestures and motion detection techniques giving a product-based outcome. Camera Sensors applying motion detection concludes if the person is present in the room. Using hand gestures recognition, fan speed can be changed and turned on/off. Expressive body actions for interacting with the physical world are better than speaking out. Today's world requires a cheap viable solution that is technologically evolved but affordable.

# Working Principle
With growth of technology in various fields, there has been a lot of digital improvements in various sectors. Now we are exploring various options to combine technology to improvise the equipment’s. Our proposed system is product based focusing on equity. Differently-abled people or Senior Citizens in using the equipment’s faces problems. But with our Proposed model we can overcome this problem.

Smart fans and ordinary fans: Normally with ordinary fans, user have to look for remote or manually rotate the switch for operating it. The technology of switch is not mobile. However, remotes are mobile. But Remotes are liabilities, which needs to be taken care of. Our proposed model is about using the gesture pointed at camera. The camera could be attached to fan if the fans are wall fans, but for ceiling fans CCTV cameras can be used. With motion detection technology used, the Smart fans technology could be further expanded into secret surveillance system.

**_Motion Detection:_** It detects the motion of the user and read it to give the corresponding response what the user wants.
-	Capture Frame: frame capture using OpenCV
-	Gaussian Blur: As sometimes the capture motions of the user can be blurred due to the incorrect focus of the camera so to reduce the image noise and reduce detail Gaussian blur is used so that the motions is clearly visible.
-	Finding contours: we used OpenCV tool to extract contours and find its movement.

**_Gesture Recognition:_** It is staple method of interaction especially for the deaf and the blind people.
- Segmentation: The image captured by the camera is divided into multiple segment.
- Otsu thresholding: It helps to return a single intensity threshold that separate   pixels into two classes, foreground and background.
- High-boost filter: It is used for amplifying high frequency components of signals and image which results in image sharpening.
- Cosine angle
- Contours counting: contours represent the shape of the object found in a image that is captured by camera.

In our proposed project, we aim to build a low cost fully automatic SMART fan using the principles of Motion Detection and Gesture Recognition .

In Gesture Recognition, we will be using segmentation, otsu thresholding (image processing method), high boost filter, contours counting.

In Motion Recognition, we will be applying capture frame, gaussian blur, finding contours.

# Proposed System Block
![Proposed System Block](https://user-images.githubusercontent.com/75159757/201636025-712a0368-5203-4719-a545-34aab71404eb.png)

# Flow Diagram
![Flow Diagram](https://user-images.githubusercontent.com/75159757/201636148-dac3bbd0-b8b0-4746-8200-90b988e5f47d.png)

# Conclusion
Our project is basically using the methods of motion detection and hand gesture recognition to make a fully automised, remote less and no-application based  FAN that functions completely based on on human gestures.
So basically initially the camera first detects whether or not room is occupied. If the room is occupied then the camera captures the hand gesture and then accordingly adjusts  the fan speed.
Our project provides a very easy prototype of computer human interaction between the smart fan and the user, it can easily be used by all age group people,  and will be very easily operated by specially abled people.

