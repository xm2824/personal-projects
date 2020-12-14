package pgdp.filetree;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class RegularFile extends File {

	public RegularFile(Path path) {
		super(path);
	}

	@Override
	public Iterator<File> iterator() {
		//* creating a new ArrayList instance on heap to return its iterator
        List<File> tmp = new ArrayList<>();
        tmp.add(this);

        //* return the iterator implemented automatically by ArrayList
        return tmp.iterator();
	}

	@Override
	public int getHeight() {
		return 0;
	}

	@Override
	public boolean isRegularFile() {
		return true;
	}

}
