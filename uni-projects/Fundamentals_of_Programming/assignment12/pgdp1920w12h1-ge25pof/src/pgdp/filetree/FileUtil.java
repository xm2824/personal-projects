package pgdp.filetree;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class FileUtil {

    /**
     * construct our File Structure with the given path.<p>
     *     If the given path corresponds a regular file, i.e. not a directory, then return a {@code RegularFile} object on heap,
     *     otherwise(directory): first construct all the subfiles/dirs from the given directory recursively, then return the reference
     *     to this directory
     * </p>
     * <p>
     *     If IOException happen, throw it further.
     * </p>
     * @param path
     * @return
     * @throws IOException
     */
	public static File toFileRepresentation(Path path) throws IOException {
		//* 0. if this is not a valid path => Exception
        if(!Files.exists(path)){
            throw new IOException();
        }

        //* 1. if it's a regular file => return a RegularFile object
        if(Files.isRegularFile(path)){
            return new RegularFile(path);
        }

        //* 2. if it's a directory
        else if (Files.isDirectory(path))
        {
            //!!! need to construct the entries recursively => write a recursive method for that
            //!!! definition: this method should return all subfiles/folders in the required structure from given directory
            return new Directory(path,_buildRecursively(path));
        }

        //??? else ???
        return null;
	}

    /**
     * build all the subfiles/dirs recursively under the given directory, and return the list of entries under this directory
     * @param dir
     * @return
     */
	private static List<File> _buildRecursively(Path dir) throws IOException {
	    // the list of files to return, possibly empty
        List<File> files = new ArrayList<>();

	    // 0. list all the entries
        Files.list(dir).forEach(p->{
            // 0.1 reg file
            if(Files.isRegularFile(p)){
                files.add(new RegularFile(p));
            }

            // 0.2 dir
            else if (Files.isDirectory(p)){
                //!!! recursive call
                try {
                    files.add(new Directory(p,_buildRecursively(p)));
                } catch (IOException e) { }
            }

            // 0.3 else: not valid path => do nothing
        });

        //* return
        return files;
	}

}
