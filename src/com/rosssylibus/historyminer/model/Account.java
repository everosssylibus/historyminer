package com.rosssylibus.historyminer.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Account {

   public Account() {

   }

   // Character name associated for the EVE SSO
   @Id
   private String characterName;

   // API key details
   private String keyID;
   private String vCode;

   
   /**
    * @return the characterId
    */
   public String getCharacterId() {
   
      return characterId;
   }

   
   /**
    * @param characterId the characterId to set
    */
   public void setCharacterId( String characterId ) {
   
      this.characterId = characterId;
   }

   // Verification of character ownership harvested from EVE SSO
   private String characterId;
   private String characterOwnerHash;

   // Permissions
   private String isAdmin;
   private String isRecruiter;

   // Statistics to be tracked
   private Date   lastLogin;

   /**
    * @return the characterName
    */
   public String getCharacterName() {

      return characterName;
   }

   /**
    * @param characterName
    *           the characterName to set
    */
   public void setCharacterName( String characterName ) {

      this.characterName = characterName;
   }

   /**
    * @return the keyID
    */
   public String getKeyID() {

      return keyID;
   }

   /**
    * @param keyID
    *           the keyID to set
    */
   public void setKeyID( String keyID ) {

      this.keyID = keyID;
   }

   /**
    * @return the vCode
    */
   public String getvCode() {

      return vCode;
   }

   /**
    * @param vCode
    *           the vCode to set
    */
   public void setvCode( String vCode ) {

      this.vCode = vCode;
   }

   /**
    * @return the characterOwnerHash
    */
   public String getCharacterOwnerHash() {

      return characterOwnerHash;
   }

   /**
    * @param characterOwnerHash
    *           the characterOwnerHash to set
    */
   public void setCharacterOwnerHash( String characterOwnerHash ) {

      this.characterOwnerHash = characterOwnerHash;
   }

   /**
    * @return the isAdmin
    */
   public String getIsAdmin() {

      return isAdmin;
   }

   /**
    * @param isAdmin
    *           the isAdmin to set
    */
   public void setIsAdmin( String isAdmin ) {

      this.isAdmin = isAdmin;
   }

   /**
    * @return the isRecruiter
    */
   public String getIsRecruiter() {

      return isRecruiter;
   }

   /**
    * @param isRecruiter
    *           the isRecruiter to set
    */
   public void setIsRecruiter( String isRecruiter ) {

      this.isRecruiter = isRecruiter;
   }

   /**
    * @return the lastLogin
    */
   public Date getLastLogin() {

      return lastLogin;
   }

   /**
    * @param lastLogin
    *           the lastLogin to set
    */
   public void setLastLogin( Date lastLogin ) {

      this.lastLogin = lastLogin;
   }
}
