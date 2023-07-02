package com.java.fullProject.repository;

/*If we don't want the whole student object to be fetched when we write JPQL or native query then we
can create a separate interface like this and put the getters of the fields that we need to display.
We will write our custom queries/JPQL in the base repository (StudentRepo in this case),which will
return StudentRepositoryDTOForFewFields type of data.(List<StudentRepositoryDTOForFewFields> getByFirstName(String firstName))
*/
public interface StudentRepositoryDTOForFewFields {
    public String getFirstName();
    public String getLastName();
}
