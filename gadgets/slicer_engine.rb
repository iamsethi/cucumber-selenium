require 'cuke_slicer'

class SlicerEngine
  attr_accessor :filters, :directory, :tags


  def initialize
    self.filters = {}
  end

  def slice
    CukeSlicer::Slicer.new.slice(self.directory, self.filters, :file_line)
  end

  def tags=(tags)
    @tags = tags
    @filters = tags_to_filter(tags)
  end

  private

  def tags_to_filter(tags)
    included_tags = []
    excluded_tags = []
    tags.each do |tag|
      tag.include?('~') ? excluded_tags << tag.delete('~') : included_tags << tag
    end
    {included_tags: included_tags, excluded_tags: excluded_tags}
  end
end