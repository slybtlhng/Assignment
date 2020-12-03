CREATE DATABASE JOURNEYTOTHEWEST
USE JOURNEYTOTHEWEST

CREATE TABLE tblUser(
	userNo INT IDENTITY(1,1) NOT NULL,
	userID VARCHAR(10) PRIMARY KEY DEFAULT '1',
	roleName VARCHAR(100) NOT NULL,
	userName NVARCHAR(100) NOT NULL UNIQUE CHECK (len(userName)>=4),
	userPass VARCHAR(100) NOT NULL CHECK (len(userPass)>=4),
	userFullName NVARCHAR(100) NOT NULL,
	userImage NVARCHAR(Max) NOT NULL,
	userDescription NVARCHAR(Max) NOT NULL,
	userEmail VARCHAR(50) NOT NULL UNIQUE CHECK(userEmail like '%__@__%.%__'),
	userPhone VARCHAR(10) UNIQUE NOT NULL CHECK(len(userPhone)=10),
	userStatus BIT NOT NULL
)



CREATE TRIGGER setUserID ON tblUser
For INSERT
AS
BEGIN
	DECLARE @userID VARCHAR(10)='U'+RIGHT('0000'+CONVERT(VARCHAR(10),(SELECT userNo FROM dbo.tblUser WHERE userID=(SELECT userID FROM inserted))),4) 
	UPDATE tblUser SET userID=@userID WHERE userID=(SELECT userID FROM inserted)
END

INSERT dbo.tblUser
(
    userID,
    roleName,
    userName,
    userPass,
    userFullName,
    userImage,
    userDescription,
    userEmail,
    userPhone,
    userStatus
)
VALUES
(   '',  -- userID - varchar(10)
    'Admin',  -- roleName - varchar(100)
    N'admin', -- userName - nvarchar(100)
    'admin',  -- userPass - varchar(100)
    N'Nguyen Thi Kim Hang', -- userFullName - nvarchar(100)
    N'admin.jpg', -- userImage - nvarchar(max)
    N'This is the only admin', -- userDescription - nvarchar(max)
    'hangntk@fpt.edu.vn',  -- userEmail - varchar(50)
    '0919799125',  -- userPhone - varchar(10)
    1 -- userStatus - bit
    )

GO
create function getRoleName (@userID as varchar(10))
returns VARCHAR(100)
AS
BEGIN
	return (SELECT roleName FROM tblUser WHERE userID = @userID)
END
GO--

CREATE TABLE tblNotification(
	notiNo INT IDENTITY(1,1) NOT NULL,
	notiID VARCHAR(10) PRIMARY KEY DEFAULT '1',
	notiDescription NVARCHAR(100) NOT NULL,
	userID VARCHAR(10) FOREIGN KEY REFERENCES dbo.tblUser(userID) ON DELETE CASCADE,
	dateCreate DATE
)


CREATE TRIGGER setNotificationID ON tblNotification
For INSERT
AS
BEGIN
	DECLARE @notiID VARCHAR(10)='N'+RIGHT('0000'+CONVERT(VARCHAR(10),(SELECT notiNo FROM dbo.tblNotification WHERE notiID=(SELECT notiID FROM inserted))),4) 
	UPDATE dbo.tblNotification SET notiID=@notiID WHERE notiID=(SELECT notiID FROM inserted)
END

CREATE TABLE tblTribulation(
	tribulationNo INT IDENTITY(1,1) NOT NULL,
	tribulationID VARCHAR(10) PRIMARY KEY DEFAULT '1',
	adminID VARCHAR(10) FOREIGN KEY REFERENCES dbo.tblUser(userID),
	constraint adminCreater CHECK (dbo.getRoleName(adminID) LIKE 'Admin'),--admin la role id=1
	directorID VARCHAR(10) FOREIGN KEY REFERENCES dbo.tblUser(userID),
	constraint directorRegist CHECK (dbo.getRoleName(directorID) LIKE 'Director'),--director role id=3
	tribulationName NVARCHAR(100) NOT NULL UNIQUE,
	tribulationDescription NVARCHAR(100) NOT NULL,
	tribulationAddress NVARCHAR(100) NOT NULL,
	tribulationStartTime DATE NOT NULL,
	tribulationEndTime DATE ,
	tribulationCount TINYINT,--so luot quay
	toolCount INT DEFAULT 0 NOT NULL CHECK (toolCount>=0), --so luong dao cu su dung
	tribulationFile NVARCHAR(max) NOT NULL, --dac ta cua vai dien, dien vien dien vai nao
	tribulationStatus BIT NOT NULL,--da hoan thanh hay chua
)


CREATE TRIGGER setTribulationID ON tblTribulation
For INSERT
AS
BEGIN
	DECLARE @tribulationID VARCHAR(10)='T'+RIGHT('0000'+CONVERT(VARCHAR(10),(SELECT tribulationNo FROM dbo.tblTribulation WHERE tribulationID=(SELECT tribulationID FROM inserted))),4) 
	UPDATE dbo.tblTribulation SET tribulationID=@tribulationID WHERE tribulationID=(SELECT tribulationID FROM inserted)
END

--bang vai dien
CREATE TABLE tblPart (
	partNo INT IDENTITY(1,1) NOT NULL,
	partID VARCHAR(10) PRIMARY KEY DEFAULT '1',
	partName NVARCHAR(100) NOT NULL,
	partDescription NVARCHAR(100) ,
	tribulationID VARCHAR(10) FOREIGN KEY REFERENCES dbo.tblTribulation(tribulationID),
)

CREATE TRIGGER setPartID ON tblPart
For INSERT
AS
BEGIN
	DECLARE @partID VARCHAR(10)='PART'+RIGHT('0000'+CONVERT(VARCHAR(10),(SELECT partNo FROM dbo.tblPart WHERE partID=(SELECT partID FROM inserted))),4) 
	UPDATE dbo.tblPart SET partID=@partID WHERE partID=(SELECT partID FROM inserted)
END

CREATE TABLE tblCastRegistration(
	castID VARCHAR(10) FOREIGN KEY REFERENCES dbo.tblUser(userID),
	constraint castRegister CHECK (dbo.getRoleName(castID) LIKE 'Cast'),
	--cast la role id =2
	partID VARCHAR(10) FOREIGN KEY REFERENCES dbo.tblPart(partID),
	directorID VARCHAR(10) FOREIGN KEY REFERENCES dbo.tblUser(userID),
	constraint directorRegistForCast CHECK (dbo.getRoleName(directorID) = 'Director'),
)

CREATE TABLE tblProperties(
	propNo INT IDENTITY(1,1) NOT NULL,
	propID VARCHAR(10) PRIMARY KEY DEFAULT '1',
	propName NVARCHAR(100) NOT NULL,
	propImage NVARCHAR(Max) NOT NULL,
	propDescription NVARCHAR(100) NOT NULL,
	propQuantity INT NOT NULL DEFAULT 0 CHECK(propQuantity>=0),--so luong co san
	propStatus BIT NOT NULL,--available hay khong
)

CREATE TRIGGER setpropID ON tblProperties
For INSERT
AS
BEGIN
	DECLARE @propID VARCHAR(10)='PROP'+RIGHT('0000'+CONVERT(VARCHAR(10),(SELECT propNo FROM dbo.tblProperties WHERE propID=(SELECT propID FROM inserted))),4) 
	UPDATE tblProperties SET propID=@propID WHERE propID=(SELECT propID FROM inserted)
END

CREATE TABLE tblTribulation_Properties (
	tribulationID VARCHAR(10) FOREIGN KEY REFERENCES dbo.tblTribulation(tribulationID),
	propID VARCHAR(10) FOREIGN KEY REFERENCES dbo.tblProperties(propID),
	propQuantity INT NOT NULL DEFAULT 0 CHECK (propQuantity>=0),--so luong su dung
	directorID VARCHAR(10) FOREIGN KEY REFERENCES dbo.tblUser(userID),
	constraint directorRegistToolForTribulation CHECK (dbo.getRoleName(directorID) LIKE 'Director'),
	timeStart DATETIME,
	timeEnd DATETIME,
)

SELECT * FROM dbo.tblTribulation_Properties
 INSERT INTO dbo.tblTribulation_Properties
 (
     tribulationID,
     propID,
     propQuantity,
     directorID,
     adminID
 )
 VALUES
 (   '', -- tribulationID - varchar(10)
     '', -- propID - varchar(10)
     0,  -- propQuantity - int
     '', -- directorID - varchar(10)
     ''  -- adminID - varchar(10)
     )

INSERT INTO dbo.tblUser
(
    roleName,
    userName,
    userPass,
    userFullName,
    userImage,
    userDescription,
    userEmail,
    userPhone,
    userStatus
)
VALUES(
    'Cast',   -- roleID - tinyint
    N'cast1', -- userName - nvarchar(100)
    'cast1',  -- userPass - varchar(100)
    N'Lam Hau Huong', -- userFullName - nvarchar(100)
    N'cast1.jpg', -- userImage - nvarchar(max)
    N'This is the first cast', -- userDescription - nvarchar(max)
    'huonglh@fpt.edu.vn',  -- userEmail - varchar(50)
    '0919799126',  -- userPhone - varchar(10)
    1 -- userStatus - bit
    )

	INSERT INTO dbo.tblUser
(
    roleName,
    userName,
    userPass,
    userFullName,
    userImage,
    userDescription,
    userEmail,
    userPhone,
    userStatus
)
VALUES(
    'Director',   -- roleID - tinyint
    N'director1', -- userName - nvarchar(100)
    'director1',  -- userPass - varchar(100)
    N'Ngo Tan Duc', -- userFullName - nvarchar(100)
    N'director1.jpg', -- userImage - nvarchar(max)
    N'This is the first director', -- userDescription - nvarchar(max)
    'ducnt@fpt.edu.vn',  -- userEmail - varchar(50)
    '0919799124',  -- userPhone - varchar(10)
    1 -- userStatus - bit
    )

	
	ALTER TABLE dbo.tblNotification 
	ADD CONSTRAINT deleteALL ON DELETE CASCADE

	SELECT * FROM dbo.tblUser 

	SELECT * FROM dbo.tblProperties
	
	SELECT * FROM dbo.tblTribulation

	SELECT * FROM dbo.tblPart
	SELECT * FROM tblCastRegistration

	INSERT INTO dbo.tblCastRegistration
	(
	    castID,
	    partID,
	    directorID
	)
	VALUES
	(   '', -- castID - varchar(10)
	    '', -- partID - varchar(10)
	    ''  -- directorID - varchar(10)
	    )
	INSERT INTO dbo.tblTribulation
	(
	    tribulationID,
	    adminID,
	    directorID,
	    tribulationName,
	    tribulationDescription,
	    tribulationAddress,
	    tribulationStartTime,
	    tribulationEndTime,
	    tribulationCount,
	    toolCount,
	    tribulationFile,
	    tribulationStatus
	)
	VALUES
	(   'T01',        -- tribulationID - varchar(10)
	    '',        -- adminID - varchar(10)
	    '',        -- directorID - varchar(10)
	    N'',       -- tribulationName - nvarchar(100)
	    N'',       -- tribulationDescription - nvarchar(100)
	    N'',       -- tribulationAddress - nvarchar(100)
	    GETDATE(), -- tribulationStartTime - date
	    GETDATE(), -- tribulationEndTime - date
	    0,         -- tribulationCount - tinyint
	    0,         -- toolCount - int
	    N'',       -- tribulationFile - nvarchar(max)
	    NULL       -- tribulationStatus - bit
	    )

		INSERT INTO dbo.tblProperties
		(
		    propID,
		    propName,
		    propImage,
		    propDescription,
		    propQuantity,
		    propStatus
		)
		VALUES
		(   '',   -- propID - varchar(10)
		    N'',  -- propName - nvarchar(100)
		    NULL, -- propImage - varbinary(max)
		    N'',  -- propDescription - nvarchar(100)
		    0,    -- propQuantity - int
		    NULL  -- propStatus - bit
		    )

			INSERT INTO dbo.tblPart
			(
			    partID,
			    partName,
			    partDescription,
			    tribulationID
			)
			VALUES
			(   '',  -- partID - varchar(10)
			    N'', -- partName - nvarchar(100)
			    N'', -- partDescription - nvarchar(100)
			    ''   -- tribulationID - varchar(10)
			    )

CREATE DATABASE ASM1DB
USE ASM1DB

CREATE TABLE TBL_emp(
EmpId VARCHAR(10) PRIMARY KEY,
PassWord VARCHAR(10),
EmpName VARCHAR(10),
Address VARCHAR(50),
)


CREATE DATABASE BlogDB
USE BlogDB

CREATE TABLE BlogEntry(
BlogId INT PRIMARY KEY,
Title VARCHAR(50),
Body VARCHAR(250),
DatePublish VARCHAR(20),
)
CREATE DATABASE FLOWERDB
USE FLOWERDB

CREATE TABLE tbl_Flower(
	ID VARCHAR(13) PRIMARY KEY,
	Location VARCHAR(100),
	FlowerName VARCHAR(100),
	Description VARCHAR(500),
	StartTime DATETIME,
	EndTime DATETIME,
)



INSERT INTO dbo.tbl_Flower
(
    ID,
    Location,
    FlowerName,
    Description,
    StartTime,
    EndTime
)
VALUES
(   '2',        -- ID - varchar(13)
    '2',        -- Location - varchar(100)
    '2',        -- FlowerName - varchar(100)
    '2',        -- Description - varchar(500)
    '20200401', -- StartTime - datetime
    '20200530'  -- EndTime - datetime
    )

	SELECT * FROM dbo.tbl_Flower

	SELECT * FROM dbo.tbl_Flower WHERE BETWEEN tbl_Flower.sta

	DELETE  FROM  dbo.tbl_Flower

	INSERT INTO BlogDB.dbo.BlogEntry.BlogEntry
	(
	    BlogId,
	    Title,
	    Body,
	    DatePublish
	)
	VALUES
	(   0,  -- BlogId - int
	    '', -- Title - varchar(50)
	    '', -- Body - varchar(250)
	    ''  -- DatePublish - varchar(20)
	    )

		SELECT * FROM BlogDB.dbo.BlogEntry

		SELECT * FROM tblTribulation
		
		Select partID, partName From dbo.tblPart Where tribulationID LIKE 'T0004' AND SELECT partID FROM tblCastRegistration 
		
		SELECT partID, partName
		FROM dbo.tblPart p
		WHERE tribulationID LIKE 'T0004' AND
		p.partID not in (select partID from dbo.tblCastRegistration where p.partID = partID);

		SELECT * FROM tblCastRegistration

		Select propID,
              propName 
       From dbo.tblProperties propStatus = 1 and propQuantity > 0

	  Select tribulationID,
                      propID,
                       propQuantity,
                        directorID,
                         timeStart,
                       timeEnd from tblTribulation_Properties where directorID like 'U0004' and timeStart >= '15072000' and timeEnd <= ?

					   INSERT INTO dbo.tbl_Flower
					   (
					       ID,
					       Location,
					       FlowerName,
					       Description,
					       StartTime,
					       EndTime
					   )
					   VALUES
					   (   '',        -- ID - varchar(13)
					       '',        -- Location - varchar(100)
					       '',        -- FlowerName - varchar(100)
					       '',        -- Description - varchar(500)
					       GETDATE(), -- StartTime - datetime
					       GETDATE()  -- EndTime - datetime
					       )

	

	SELECT P.partID FROM tblPart P,
					 tblCastRegistration C 
					  WHERE P.partID=C.partID AND c.castID LIKE 'U0002' AND P.tribulationID LIKE 'T0020'

					  SELECT * FROM tblCastRegistration WHERE partID LIKE 'PART0090'

					  DELETE FROM tblCastRegistration WHERE partID LIKE 'PART0090'
					  
					  SELECT partID FROM tblCastRegistration WHERE partID LIKE 'PART0090'

					  DELETE FROM tblPart

					  Delete dbo.tblTribulation_Properties where tribulationID like 'T0021'

					  SELECT partID FROM tblPart 
                    	WHERE tribulationID LIKE 'T0020'

						Delete dbo.tblTribulation where tribulationID like 'T0020'

						DELETE FROM dbo.tblTribulation

						DELETE FROM tblTribulation_Properties

						SELECT* FROM tblCastRegistration

						DELETE tblTribulation WHERE tribulationID LIKE 

						Delete dbo.tblPart where tribulationID LIKE 'T0020'

						SELECT * FROM tblUser

						SELECT * FROM dbo.tblNotification

						SELECT notiDescription , dateCreate FROM tblNotification WHERE userID LIKE ?
						INSERT INTO dbo.tblNotification
(
    notiDescription,
    userID,
    dateCreate
)
VALUES
(     N'hihihihihi',      -- notiDescription - nvarchar(100)
    'U0006',       -- userID - varchar(10)
    '20050515' -- dateCreate - date
    )

	SELECT * FROM dbo.tblProperties

	SELECT * FROM tblTribulation_Properties