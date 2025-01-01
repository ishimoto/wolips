<img src="https://wiki.wocommunity.org/xwiki/bin/download/WOL/Home/WebHome/icon_256x256.png" alt="WOLips Icon" width="30%" style="float: right;"/>

# TBLips (WOLips)

TBLips is the Eclipse feature for developing TreasureBoat applications.


GitRepo : ishimoto/wolips  
Branch	: tblips

## Java Version 21

TreasureBoat is still compiling with Java 10, but in development we are moving to sue Java21 Engine.
Because Eclipse also is using Java 21 now. For deployment Java17 and Java 21 is valid.

[Java 21](https://www.azul.com/downloads/?version=java-21-lts&os=macos&package=jdk#zulu)

## sdkman (or something else)

```
	// https://sdkman.io
	
	$ curl -s "https://get.sdkman.io"
```

## Maven 3.9.9

```
	// https://sdkman.io/sdks#maven	
	$ sdk install maven
```

---

# Setup


## Open and Switch Perspective

In the eGit perspective, add the WOLips github repository

![OpenPerspective](https://raw.githubusercontent.com/ishimoto/wolips/refs/heads/tblips/images/EAFA6801-B223-46D6-9B91-86FEE08AA9C2.png)

![SelectPerspective](https://raw.githubusercontent.com/ishimoto/wolips/refs/heads/tblips/images/BD2175A2-E931-4688-AC59-3D001B79CC62.png)

![GitView](https://raw.githubusercontent.com/ishimoto/wolips/refs/heads/tblips/images/9CFE9B0B-97B7-4BA5-8C51-0C7D7C547453.png)

---

## Add and Select WOLips (Branch TBLips)

![AddGit](https://raw.githubusercontent.com/ishimoto/wolips/refs/heads/tblips/images/06FCF121-12F4-4388-8966-A57BD0D44509.png)

![SelectPath](https://raw.githubusercontent.com/ishimoto/wolips/refs/heads/tblips/images/220754AD-5FFB-4393-9DCA-E29B3C79AB59.png)

![AfterAdd](https://raw.githubusercontent.com/ishimoto/wolips/refs/heads/tblips/images/46579D54-7C1E-4AE7-94E6-BAC664AD9BD0.png)

---

## Import Project

Right click on your repository's "Working Tree" and "Import Projects..." to import the WOLips project

![Import1](https://raw.githubusercontent.com/ishimoto/wolips/refs/heads/tblips/images/773E319A-5539-4082-AB1D-D1A53FA97DE5.png)

![Import2](https://raw.githubusercontent.com/ishimoto/wolips/refs/heads/tblips/images/F54B0AE4-6F6A-4293-9DDA-96ADBCF4298D.png)

![Import3](https://raw.githubusercontent.com/ishimoto/wolips/refs/heads/tblips/images/17107AF7-23E2-49BB-A6E9-A21AE8F795CD.png)

---

## Compile


---

## Run WOLips in Eclipse

To run WOLips with your Eclipse installation and all your existing plugins

![RunSelection](https://raw.githubusercontent.com/ishimoto/wolips/refs/heads/tblips/images/B1D38CA8-7097-4749-87B0-9C46BECA5E29.png)

---

## Config Launch Parameters

![Eclipse1](https://raw.githubusercontent.com/ishimoto/wolips/refs/heads/tblips/images/471050CA-96DD-4782-AE5A-3A3D6E147BBB.png)

![Eclipse2](https://raw.githubusercontent.com/ishimoto/wolips/refs/heads/tblips/images/C173AAD6-05C0-4D9D-BB52-0243C98B6748.png)

---



## Compile

```
	$ mvn clean package
```

## Testing

After the Compile is done the final project is saved in 

	`wolips/wolips.p2/target/repository/`
	
The version number is timestamped, so you can immediately update your local tplips install after a fresh build if you desire.	


## Deploy

After we have the final version we will provide a deploy path/


---
