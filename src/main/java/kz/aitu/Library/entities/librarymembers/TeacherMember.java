package kz.aitu.Library.entities.librarymembers;

public class TeacherMember extends LibraryMember {
    public TeacherMember(int id, String name) {
        super(id, name);
    }

    @Override
    public int getMaxBorrowCount() {
        return 6;
    }
}
