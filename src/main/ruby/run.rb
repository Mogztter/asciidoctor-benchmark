require 'asciidoctor'

base_dir = File.join(Dir.pwd, 'fixtures')
content = File.open(File.join(base_dir, 'userguide.adoc'), 'r').read

(1..4).each do |i|
  start = Time.now
  html = Asciidoctor.convert content,
                             safe: :safe,
                             base_dir: base_dir,
                             doctype: 'article',
                             header_footer: true,
                             attributes: 'linkcss copycss! toc! numbered! icons! compat-mode'
  duration = (Time.now - start) * 1000.0
  p "Run #{i}: #{duration}ms"
end

