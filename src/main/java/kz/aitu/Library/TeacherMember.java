package kz.aitu.Library;

import kz.aitu.Library.entities.LibraryMember;

public class TeacherMember extends LibraryMember {
    public TeacherMember(int id, String name) {
        super(id, name);
    }

    @Override
    public int getMaxBorrowCount() {
        return 6;
    }
}
