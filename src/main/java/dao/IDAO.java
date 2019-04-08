package dao;

/**
 * Generic inteface for all DAOS describes the minimal function set for each
 * 
 * @author Ryan
 * @param <T> represents the type of item being interacted with
 */
public interface IDAO<T> {
    /**
     * Get an item from the database with matching id
     * 
     * @param id the id of the item to be returned
     * @return an object of type <T> that matches the id provided
     */
    public T read(int id);

    /**
     * Update an item that matches the id of the item in toUpdate and change the
     * data to match toUpdate
     * 
     * @param toUpdate item to update (old id, new data)
     * @return
     */
    public T update(T toUpdate);

    /**
     * Add an item to the database with the provided information
     * 
     * @param toAdd item to add(no ID + new data)
     * @return return an item of type <T> that has the data provided in toAdd
     */
    public T create(T toAdd);

    /**
     * Remove an item from the database at a given id
     * 
     * @param id the id of the item to be removed
     */
    public void delete(int id);

}
