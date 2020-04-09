# User - Use Cases



| API | Method | Description |
| ------ | ------ | ------ |
| /login | POST | ... |
| /logout | ... | ... | 
| /profile | GET | ... | 
| /profile | POST | ... | 



## User login
Given a user is a Seller/Buyer,\
When the user submits username & password,\
Then the platform authenticates user to access our API.

```
POST /login

Parameters
* username (required): username of user which can be email.
* password (required): password of user.
```
#### Sample Request
```
{
    "username": "m.nguyencntt",
    "password": "anz@12345"
}
```
#### Sample Success Response
```
{
    "status": 200,
    "message": "User loged in success",
    "data": [
        {
            "user": {
                "userId": "UI12345",
                "username": "m.nguyencntt",
                "name": "Nguyen Van Minh",
                "dob": "01-01-1991",
                "gender": "male",
                "isActivated": "true",
                "status": "Account is activated",
                "userType": "SELLER",
                "lastLogin": "2020/04/04 12:12:12 +0800",
                "imageAvatarUrl": "https://www.google.com/mnguyencntt_avatar.png",
                "baseAddressId": "ADI12345"
            },
            "expiresAt": "2020/05/05 12:12:12 +0800",
            "authtoken": "9x8869x31134x7906x6x54474x21x18xxx90857x"
        }
    ]
}
```
#### Sample Fail Response
```
{
    "status": 401,
    "message": "Invalid username or password",
    "data": null
}
```



## User logout
Given a user is a Seller/Buyer,\
When the user submits username & authtoken,\
Then the platform allows user to logout (remove authtoken).

```
POST /logout

Parameters
* username (required): username of user which can be email.
* authtoken (required): authtoken of user.
```
#### Sample Request
```
{
    "username": "m.nguyencntt",
    "authtoken": "9x8869x31134x7906x6x54474x21x18xxx90857x"
}
```
#### Sample Success Response
```
{
    "status": 200,
    "message": "User logged out",
    "data": null
}
```
#### Sample Fail Response
```
{
    "status": 400,
    "message": "Invalid authorization token provided",
    "data": null
}
```



## User profile
Given a user is a Seller/Buyer,\
When the user submits userId & username,\
Then the platform allows user to view their profile detail with address.

```
GET /profile

Parameters
* userId (required): userId of user.
* username (required): username of user which can be email.
```
#### Sample Request
```
{
    "userId": "UI12345",
    "username": "m.nguyencntt"
}
```
#### Sample Success Response
```
{
    "status": 200,
    "message": null,
    "data": [
        {
            "user": {
                "userId": "UI12345",
                "username": "m.nguyencntt",
                "name": "Nguyen Van Minh",
                "dob": "01-01-1991",
                "gender": "male",
                "isActivated": "true",
                "status": "Account is activated",
                "userType": "SELLER",
                "lastLogin": "2020/04/04 12:12:12 +0800",
                "imageAvatarUrl": "https://www.google.com/mnguyencntt_avatar.png",
                "address": {
					"addressId": "AI12345",
					"address": "145 Mei Ling Str",
					"postcode": "140145",
					"email": "m.nguyencntt@gmail.com",
					"handPhone": "93767011"
				}
            }
        }
    ]
}
```



