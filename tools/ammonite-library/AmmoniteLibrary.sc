import $file.AmmoniteInitialize

val x = 1
@main
def main(i: Int, s: String, path: os.Path = os.pwd) = {
  s"Hello! ${s * i} ${path.last}."
}
//val fileTest = classOF[test.ammonite.ops.Testing]
//val fileContents = read! resource(fileTest)/'tools/"checkme.txt"
//assert(fileContents.contains("Ammonite"))
//val result = %%('bash, "-c", "echo \"$ENV_ARG\"", ENV_ARG=2)
