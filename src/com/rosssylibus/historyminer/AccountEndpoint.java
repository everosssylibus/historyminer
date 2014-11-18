package com.rosssylibus.historyminer;

import com.rosssylibus.historyminer.data.TransOptionalEMF;
import com.rosssylibus.historyminer.model.Account;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.response.CollectionResponse;
import com.google.appengine.api.datastore.Cursor;
import com.google.appengine.datanucleus.query.JPACursorHelper;

import java.util.List;

import javax.annotation.Nullable;
import javax.inject.Named;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import javax.persistence.EntityManager;
import javax.persistence.Query;

@Api( name = "accountendpoint", namespace = @ApiNamespace( ownerDomain = "rosssylibus.com", ownerName = "rosssylibus.com", packagePath = "historyminer.model" ) )
public class AccountEndpoint {

   /**
    * This method lists all the entities inserted in datastore. It uses HTTP GET
    * method and paging support.
    *
    * @return A CollectionResponse class containing the list of all entities
    *         persisted and a cursor to the next page.
    */
   @SuppressWarnings( { "unchecked", "unused" } )
   @ApiMethod( name = "listAccount" )
   public CollectionResponse<Account> listAccount(
         @Nullable @Named( "cursor" ) String cursorString,
         @Nullable @Named( "limit" ) Integer limit ) {

      EntityManager mgr = null;
      Cursor cursor = null;
      List<Account> execute = null;

      try {
         mgr = getEntityManager();
         Query query = mgr.createQuery( "select from Account as Account" );
         if( cursorString != null && cursorString != "" ) {
            cursor = Cursor.fromWebSafeString( cursorString );
            query.setHint( JPACursorHelper.CURSOR_HINT, cursor );
         }

         if( limit != null ) {
            query.setFirstResult( 0 );
            query.setMaxResults( limit );
         }

         execute = ( List<Account> )query.getResultList();
         cursor = JPACursorHelper.getCursor( execute );
         if( cursor != null )
            cursorString = cursor.toWebSafeString();

         // Tight loop for fetching all entities from datastore and accomodate
         // for lazy fetch.
         for( Account obj : execute )
            ;
      }
      finally {
         mgr.close();
      }

      return CollectionResponse.<Account> builder().setItems( execute )
            .setNextPageToken( cursorString ).build();
   }

   /**
    * This method gets the entity having primary key id. It uses HTTP GET
    * method.
    *
    * @param id
    *           the primary key of the java bean.
    * @return The entity with primary key id.
    */
   @ApiMethod( name = "getAccount" )
   public Account getAccount( @Named( "id" ) String id ) {

      EntityManager mgr = getEntityManager();
      Account account = null;
      try {
         account = mgr.find( Account.class, id );
      }
      finally {
         mgr.close();
      }
      return account;
   }

   /**
    * This inserts a new entity into App Engine datastore. If the entity already
    * exists in the datastore, an exception is thrown. It uses HTTP POST method.
    *
    * @param account
    *           the entity to be inserted.
    * @return The inserted entity.
    */
   @ApiMethod( name = "insertAccount" )
   public Account insertAccount( Account account ) {

      EntityManager mgr = getEntityManager();
      try {
         if( containsAccount( account ) ) {
            throw new EntityExistsException( "Object already exists" );
         }
         mgr.persist( account );
      }
      finally {
         mgr.close();
      }
      return account;
   }

   /**
    * This method is used for updating an existing entity. If the entity does
    * not exist in the datastore, an exception is thrown. It uses HTTP PUT
    * method.
    *
    * @param account
    *           the entity to be updated.
    * @return The updated entity.
    */
   @ApiMethod( name = "updateAccount" )
   public Account updateAccount( Account account ) {

      EntityManager mgr = getEntityManager();
      try {
         if( !containsAccount( account ) ) {
            throw new EntityNotFoundException( "Object does not exist" );
         }
         mgr.persist( account );
      }
      finally {
         mgr.close();
      }
      return account;
   }

   /**
    * This method removes the entity with primary key id. It uses HTTP DELETE
    * method.
    *
    * @param id
    *           the primary key of the entity to be deleted.
    */
   @ApiMethod( name = "removeAccount" )
   public void removeAccount( @Named( "id" ) String id ) {

      EntityManager mgr = getEntityManager();
      try {
         Account account = mgr.find( Account.class, id );
         mgr.remove( account );
      }
      finally {
         mgr.close();
      }
   }

   private boolean containsAccount( Account account ) {

      EntityManager mgr = getEntityManager();
      boolean contains = true;
      try {
         Account item = mgr.find( Account.class, account.getCharacterName() );
         if( item == null ) {
            contains = false;
         }
      }
      finally {
         mgr.close();
      }
      return contains;
   }

   private static EntityManager getEntityManager() {

      return TransOptionalEMF.get().createEntityManager();
   }

}
