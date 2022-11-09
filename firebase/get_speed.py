from firebase import firebase
firebase = firebase.FirebaseApplication('https://gestfanz-default-rtdb.firebaseio.com/', None)
speed_result = firebase.get('/fan_speed', '')
print(speed_result['fanSpeed'])